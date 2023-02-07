package com.nihilus13.pixabay.fragment.details.state

import com.nihilus13.domain.usecase.LoadDetailsResult
import javax.inject.Inject

internal class DetailsReducer @Inject constructor() {

    fun reduceDetails(viewState: DetailsViewState, details: LoadDetailsResult): DetailsViewState =
        when (details) {
            LoadDetailsResult.Error -> DetailsViewState.Error(viewState.detailsId)
            is LoadDetailsResult.Success -> DetailsViewState.Data(
                detailsId = viewState.detailsId,
                result = details.result
            )
        }
}
