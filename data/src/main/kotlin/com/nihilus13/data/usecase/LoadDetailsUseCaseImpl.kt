package com.nihilus13.data.usecase

import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.usecase.LoadDetailsUseCase

internal class LoadDetailsUseCaseImpl : LoadDetailsUseCase {

    override suspend fun loadDetails(id: String): List<DetailsResult> {
        TODO("Not yet implemented")
    }
}