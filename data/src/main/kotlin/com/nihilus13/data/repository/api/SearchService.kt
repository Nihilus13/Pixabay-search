package com.nihilus13.data.repository.api

import com.nihilus13.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface SearchService {

    @GET
    suspend fun searchImages(@Query(QUERY_SEARCH_TEXT) searchText: String): SearchResponse

    @GET
    suspend fun refreshImage(@Query(QUERY_DETAILS_ID) hitId: String): SearchResponse

    private companion object {
        const val QUERY_SEARCH_TEXT = "q"
        const val QUERY_DETAILS_ID = "id"
    }
}
