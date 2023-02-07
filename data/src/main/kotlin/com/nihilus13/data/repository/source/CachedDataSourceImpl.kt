package com.nihilus13.data.repository.source

import com.nihilus13.coroutines.safeapi.ContentResponse
import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.SearchResult
import com.nihilus13.domain.repository.source.CachedDataSource
import javax.inject.Inject

internal class CachedDataSourceImpl @Inject constructor() : CachedDataSource {

    override suspend fun searchForImages(searchText: String): ContentResponse<List<SearchResult>> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailedImage(detaildId: String): ContentResponse<DetailsResult> {
        TODO("Not yet implemented")
    }
}
