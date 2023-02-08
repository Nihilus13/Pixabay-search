package com.nihilus13.data.repository

import android.accounts.NetworkErrorException
import com.nihilus13.coroutines.safeapi.ContentResponse
import com.nihilus13.data.TestDataProvider.HIT_ID
import com.nihilus13.data.TestDataProvider.SEARCH_TEXT
import com.nihilus13.data.TestDataProvider.hitData
import com.nihilus13.data.TestDataProvider.searchRecord
import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.SearchResult
import com.nihilus13.domain.repository.source.CachedDataSource
import com.nihilus13.domain.repository.source.RemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class PixabayRepositoryImplTest {

    private val exception = NetworkErrorException()
    private val cachedDataSource: CachedDataSource = mock()
    private val remoteDataSource: RemoteDataSource = mock()
    private val pixabayRepository = PixabayRepositoryImpl(
        cachedDataSource = cachedDataSource,
        remoteDataSource = remoteDataSource
    )

    @Test
    fun `searches for images`() = runBlocking {
        given { runBlocking { remoteDataSource.searchForImages(SEARCH_TEXT) } }
            .willReturn(ContentResponse.Success(SearchResult.Data(searchRecord)))

        pixabayRepository.searchForImages(SEARCH_TEXT)

        verify(cachedDataSource).insertHitData(searchRecord)
        verify(cachedDataSource).searchForImages(SEARCH_TEXT)
    }

    @Test
    fun `searches for images with error`() = runBlocking {
        given { runBlocking { remoteDataSource.searchForImages(SEARCH_TEXT) } }
            .willReturn(ContentResponse.Failure(exception))

        pixabayRepository.searchForImages(SEARCH_TEXT)

        verify(cachedDataSource).searchForImages(SEARCH_TEXT)
    }

    @Test
    fun `fetches details`() = runBlocking {
        given { runBlocking { remoteDataSource.fetchDetails(HIT_ID) } }
            .willReturn(ContentResponse.Success(DetailsResult.Data(hitData)))

        pixabayRepository.fetchDetails(HIT_ID)

        verify(cachedDataSource).insertHitData(searchRecord)
        verify(cachedDataSource).getDetailedImage(SEARCH_TEXT)
    }

    @Test
    fun `fetches details with error`() = runBlocking {
        given { runBlocking { remoteDataSource.fetchDetails(HIT_ID) } }
            .willReturn(ContentResponse.Failure(exception))

        pixabayRepository.fetchDetails(HIT_ID)

        verify(cachedDataSource).getDetailedImage(SEARCH_TEXT)
    }
}