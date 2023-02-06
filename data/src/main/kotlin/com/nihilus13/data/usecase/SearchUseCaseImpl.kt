package com.nihilus13.data.usecase

import com.nihilus13.domain.model.SearchResult
import com.nihilus13.domain.repository.SearchRepository
import com.nihilus13.domain.usecase.SearchUseCase
import javax.inject.Inject

internal class SearchUseCaseImpl @Inject constructor(private val repository: SearchRepository) :
    SearchUseCase {

    override suspend fun searchForImages(searchText: String): List<SearchResult> =
        repository.searchForImages(searchText)
}
