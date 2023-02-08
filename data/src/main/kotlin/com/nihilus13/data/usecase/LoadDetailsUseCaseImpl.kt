package com.nihilus13.data.usecase

import com.nihilus13.domain.repository.PixabayRepository
import com.nihilus13.domain.usecase.LoadDetailsUseCase
import javax.inject.Inject

internal class LoadDetailsUseCaseImpl @Inject constructor(private val pixabayRepository: PixabayRepository) :
    LoadDetailsUseCase {

    override suspend fun loadDetails(id: String) = pixabayRepository.fetchDetails(id)
}
