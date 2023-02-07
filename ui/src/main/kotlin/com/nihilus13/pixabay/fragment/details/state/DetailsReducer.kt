package com.nihilus13.pixabay.fragment.details.state

import com.nihilus13.domain.model.DetailsResult
import javax.inject.Inject

internal class DetailsReducer @Inject constructor() {

    fun reduceDetails(viewState: DetailsViewState, details: DetailsResult): DetailsViewState =
        when (viewState) {
            is DetailsViewState.Data -> TODO()
            is DetailsViewState.Error -> TODO()
            is DetailsViewState.Pending -> TODO()
        }
}
