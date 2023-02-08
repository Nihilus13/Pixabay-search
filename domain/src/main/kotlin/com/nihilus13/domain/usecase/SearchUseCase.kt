package com.nihilus13.domain.usecase

import com.nihilus13.domain.model.SearchResult

interface SearchUseCase {

    suspend fun searchForImages(searchText: String): SearchResult
}
