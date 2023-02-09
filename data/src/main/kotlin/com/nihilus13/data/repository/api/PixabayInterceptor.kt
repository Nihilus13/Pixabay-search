package com.nihilus13.data.repository.api

import com.nihilus13.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class PixabayInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url.newBuilder()
            .addQueryParameter(HEADER_API_KEY, BuildConfig.API_KEY)
            .build()

        val requestBuilder = request.newBuilder().url(url)

        return chain.proceed(requestBuilder.build())
    }

    private companion object {
        const val HEADER_API_KEY = "key"
    }
}
