package com.nihilus13.data.usecase

import com.nihilus13.domain.model.SearchResult
import com.nihilus13.domain.repository.PixabayRepository
import com.nihilus13.domain.usecase.SearchUseCase
import javax.inject.Inject

internal class SearchUseCaseImpl @Inject constructor(private val pixabayRepository: PixabayRepository) :
    SearchUseCase {

    override suspend fun searchForImages(searchText: String): SearchResult =
        pixabayRepository.searchForImages(searchText)
}
