package com.nihilus13.domain.repository.source

import com.nihilus13.coroutines.safeapi.ContentResponse
import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.HitData
import com.nihilus13.domain.model.SearchRecord
import com.nihilus13.domain.model.SearchResult

interface RemoteDataSource {
    suspend fun searchForImages(searchText: String): ContentResponse<SearchResult.Data>
    suspend fun fetchDetails(hitId: String): ContentResponse<DetailsResult.Data>
}

interface CachedDataSource {
    suspend fun insertHitData(data: SearchRecord)
    suspend fun insertHitData(data: HitData)
    suspend fun searchForImages(searchText: String): SearchResult
    suspend fun getDetailedImage(hitId: String): DetailsResult
}