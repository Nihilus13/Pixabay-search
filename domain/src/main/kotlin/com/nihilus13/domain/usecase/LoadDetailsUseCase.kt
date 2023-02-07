package com.nihilus13.domain.usecase

import com.nihilus13.domain.model.DetailsResult

interface LoadDetailsUseCase {

    suspend fun loadDetails(id: String): DetailsResult
}
