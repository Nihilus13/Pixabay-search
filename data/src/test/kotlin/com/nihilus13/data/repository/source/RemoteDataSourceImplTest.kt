package com.nihilus13.data.repository.source

import android.accounts.NetworkErrorException
import com.google.common.truth.Truth.assertThat
import com.nihilus13.coroutines.safeapi.ContentResponse
import com.nihilus13.data.TestDataProvider.SEARCH_TEXT
import com.nihilus13.data.TestDataProvider.hitData
import com.nihilus13.data.TestDataProvider.response
import com.nihilus13.data.TestDataProvider.searchRecord
import com.nihilus13.data.repository.api.SearchService
import com.nihilus13.data.repository.mapper.DomainNetworkMapper
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

internal class RemoteDataSourceImplTest {

    private val exception = NetworkErrorException()
    private val searchService: SearchService = mock()
    private val mapper = DomainNetworkMapper()
    private val remoteDataSource = RemoteDataSourceImpl(searchService, mapper)

    @Test
    fun `searches in service for images`() = runBlocking {
        given { runBlocking { searchService.searchImages(SEARCH_TEXT) } }.willReturn(response)

        val result = remoteDataSource.searchForImages(SEARCH_TEXT)

        assertThat(result).isEqualTo(ContentResponse.Success(searchRecord))
    }

    @Test
    fun `searches in service for images with an error`() = runBlocking {
        given { runBlocking { searchService.searchImages(SEARCH_TEXT) } }
            .willThrow(exception)

        val result = remoteDataSource.searchForImages(SEARCH_TEXT)

        assertThat(result).isEqualTo(ContentResponse.Failure(exception))
    }

    @Test
    fun `refreshes image`() = runBlocking {
        given { runBlocking { searchService.refreshImage(SEARCH_TEXT) } }.willReturn(response)

        val result = remoteDataSource.fetchDetails(SEARCH_TEXT)

        assertThat(result).isEqualTo(ContentResponse.Success(hitData))
    }

    @Test
    fun `refreshes image with an error`() = runBlocking {
        given { runBlocking { searchService.refreshImage(SEARCH_TEXT) } }.willThrow(exception)

        val result = remoteDataSource.fetchDetails(SEARCH_TEXT)

        assertThat(result).isEqualTo(ContentResponse.Failure(exception))
    }
}