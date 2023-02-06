package com.nihilus13.data.repository.source

import com.nihilus13.coroutines.safeapi.ContentResponse
import com.nihilus13.coroutines.safeapi.asContentResponse
import com.nihilus13.data.repository.api.SearchService
import com.nihilus13.data.repository.mapper.DomainMapper
import com.nihilus13.domain.model.SearchResult
import com.nihilus13.domain.repository.source.RemoteDataSource
import javax.inject.Inject

internal class RemoteDataSourceImpl @Inject constructor(
    private val mapper: DomainMapper,
    private val searchService: SearchService
) : RemoteDataSource {

    override suspend fun searchForImages(searchText: String): ContentResponse<List<SearchResult>> =
        asContentResponse {
            val response = searchService.searchImages(searchText)
            mapper.mapResponse(response)
        }
}
