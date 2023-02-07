package com.nihilus13.data.usecase

import com.nihilus13.domain.usecase.LoadDetailsResult
import com.nihilus13.domain.usecase.LoadDetailsUseCase

internal class LoadDetailsUseCaseImpl : LoadDetailsUseCase {

    override suspend fun loadDetails(id: String): LoadDetailsResult {

        return LoadDetailsResult.Error
    }
}
