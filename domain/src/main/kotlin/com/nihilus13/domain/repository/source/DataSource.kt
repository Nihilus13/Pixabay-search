package com.nihilus13.domain.repository.source

import com.nihilus13.coroutines.safeapi.ContentResponse
import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.SearchResult

interface RemoteDataSource {
    suspend fun searchForImages(searchText: String): ContentResponse<List<SearchResult>>
}

interface CachedDataSource {
    suspend fun searchForImages(searchText: String): ContentResponse<List<SearchResult>>
    suspend fun getDetailedImage(detaildId: String): ContentResponse<DetailsResult>
}