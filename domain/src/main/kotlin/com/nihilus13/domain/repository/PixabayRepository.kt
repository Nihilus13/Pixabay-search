package com.nihilus13.domain.repository

import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.SearchResult

interface PixabayRepository {

    suspend fun searchForImages(searchText: String): SearchResult

    suspend fun fetchDetails(hitId: String): DetailsResult
}
