package com.nihilus13.data.repository.source

import com.nihilus13.coroutines.safeapi.ContentResponse
import com.nihilus13.coroutines.safeapi.asContentResponse
import com.nihilus13.data.repository.api.SearchService
import com.nihilus13.data.repository.mapper.DomainNetworkMapper
import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.SearchResult
import com.nihilus13.domain.repository.source.RemoteDataSource
import javax.inject.Inject

internal class RemoteDataSourceImpl @Inject constructor(
    private val searchService: SearchService,
    private val mapper: DomainNetworkMapper
) : RemoteDataSource {

    override suspend fun searchForImages(searchText: String): ContentResponse<SearchResult.Data> =
        asContentResponse {
            val response = searchService.searchImages(searchText)
            mapper.mapSearchResponse(searchText, response)
        }

    override suspend fun fetchDetails(hitId: String): ContentResponse<DetailsResult.Data> =
        asContentResponse {
            val response = searchService.refreshImage(hitId)
            mapper.mapSearchResponse(response)
        }
}
