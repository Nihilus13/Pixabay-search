package com.nihilus13.data.repository.source

import com.google.common.truth.Truth.assertThat
import com.nihilus13.coroutines.safeapi.ContentResponse
import com.nihilus13.data.TestDataProvider.SEARCH_TEXT
import com.nihilus13.data.TestDataProvider.hitData
import com.nihilus13.data.TestDataProvider.response
import com.nihilus13.data.TestDataProvider.searchRecord
import com.nihilus13.data.repository.api.SearchService
import com.nihilus13.data.repository.mapper.DomainNetworkMapper
import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.SearchResult
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class RemoteDataSourceImplTest {

    private val exception = RuntimeException()
    private val searchService: SearchService = mock()
    private val mapper = DomainNetworkMapper()
    private val remoteDataSource = RemoteDataSourceImpl(searchService, mapper)

    @Test
    fun `searches in service for images`() = runBlocking {
        whenever(searchService.searchImages(SEARCH_TEXT)).thenReturn(response)

        val result = remoteDataSource.searchForImages(SEARCH_TEXT)

        assertThat(result).isEqualTo(ContentResponse.Success(SearchResult.Data(searchRecord)))
    }

    @Test
    fun `searches in service for images with an error`() = runBlocking {
        whenever(searchService.searchImages(SEARCH_TEXT)).thenThrow(exception)

        val result = remoteDataSource.searchForImages(SEARCH_TEXT)

        assertThat(result).isEqualTo(ContentResponse.Failure(exception))
    }

    @Test
    fun `refreshes image`() = runBlocking {
        whenever(searchService.refreshImage(SEARCH_TEXT)).thenReturn(response)

        val result = remoteDataSource.fetchDetails(SEARCH_TEXT)

        assertThat(result).isEqualTo(ContentResponse.Success(DetailsResult.Data(hitData)))
    }

    @Test
    fun `refreshes image with an error`() = runBlocking {
        whenever(searchService.refreshImage(SEARCH_TEXT)).thenThrow(exception)

        val result = remoteDataSource.fetchDetails(SEARCH_TEXT)

        assertThat(result).isEqualTo(ContentResponse.Failure(exception))
    }
}