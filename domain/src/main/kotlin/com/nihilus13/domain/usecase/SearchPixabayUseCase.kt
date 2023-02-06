package com.nihilus13.domain.usecase

import com.nihilus13.domain.model.SearchResult

interface SearchPixabayUseCase {

    suspend fun searchForImages(searchText: String): List<SearchResult>
}