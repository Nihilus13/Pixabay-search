package com.nihilus13.data.repository

import com.nihilus13.coroutines.safeapi.ContentResponse
import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.HitData
import com.nihilus13.domain.model.SearchRecord
import com.nihilus13.domain.model.SearchResult
import com.nihilus13.domain.repository.PixabayRepository
import com.nihilus13.domain.repository.source.CachedDataSource
import com.nihilus13.domain.repository.source.RemoteDataSource
import javax.inject.Inject

internal class PixabayRepositoryImpl @Inject constructor(
    private val cachedDataSource: CachedDataSource,
    private val remoteDataSource: RemoteDataSource
) : PixabayRepository {

    override suspend fun searchForImages(searchText: String): SearchResult =
        when (val result = remoteDataSource.searchForImages(searchText)) {
            is ContentResponse.Failure -> cachedDataSource.searchForImages(searchText)
            is ContentResponse.Success -> result.getData()
                .also { insertSearchRecord(it.record) }
        }

    private suspend fun insertSearchRecord(data: SearchRecord) =
        cachedDataSource.insertHitData(data)

    override suspend fun fetchDetails(hitId: String): DetailsResult =
        when (val result = remoteDataSource.fetchDetails(hitId)) {
            is ContentResponse.Failure -> cachedDataSource.getDetailedHit(hitId)
            is ContentResponse.Success -> result.getData()
                .also { insertHitData(it.data) }
        }

    private suspend fun insertHitData(data: HitData) = cachedDataSource.insertHitData(data)
}
