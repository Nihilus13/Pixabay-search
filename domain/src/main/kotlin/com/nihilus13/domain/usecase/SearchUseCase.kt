package com.nihilus13.domain.usecase

import com.nihilus13.domain.model.SearchResult

interface SearchUseCase {

    suspend fun searchForImages(searchText: String): SearchForImageResult
}

sealed class SearchForImageResult {
    data class Success(val results: List<SearchResult>) : SearchForImageResult()
    object Error : SearchForImageResult()
}