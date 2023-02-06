package com.nihilus13.data.usecase

import com.nihilus13.domain.model.SearchResult
import com.nihilus13.domain.repository.SearchRepository
import com.nihilus13.domain.usecase.SearchPixabayUseCase
import javax.inject.Inject

internal class SearchPixabayUseCaseImpl @Inject constructor(private val repository: SearchRepository) :
    SearchPixabayUseCase {

    override suspend fun searchForImages(searchText: String): List<SearchResult> =
        repository.searchForImages(searchText)
}
