package com.nihilus13.data.repository.api

import com.nihilus13.data.model.SearchResponse
import retrofit2.http.GET

internal interface SearchService {

    @GET
    suspend fun searchImages(searchText: String): SearchResponse

    @GET
    suspend fun refreshImage(imageId: String): SearchResponse
}
