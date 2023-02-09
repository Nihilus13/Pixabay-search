package com.nihilus13.data.repository.api

import com.google.common.truth.Truth.assertThat
import com.nihilus13.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class PixabayInterceptorTest {

    private val mockWebServer = MockWebServer()
    private val interceptor = PixabayInterceptor()
    private val request: Request
        get() = Request.Builder().url(mockWebServer.url("")).build()
    private lateinit var okHttpClient: OkHttpClient

    @Before
    fun setup() {
        mockWebServer.start()
        mockWebServer.enqueue(MockResponse())
        okHttpClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    @Test
    fun `checks whether pixabay api key header has been added`() {
        okHttpClient.newCall(request).execute()
        val request = mockWebServer.takeRequest()
        val param = request.requestUrl?.queryParameter(HEADER_API_KEY)
        assertThat(param).isEqualTo(BuildConfig.API_KEY)
    }

    @After
    fun teardown() {
        mockWebServer.close()
    }

    private companion object {
        const val HEADER_API_KEY = "key"
    }
}