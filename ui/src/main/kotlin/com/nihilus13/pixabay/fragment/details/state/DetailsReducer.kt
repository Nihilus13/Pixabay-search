package com.nihilus13.pixabay.fragment.details.state

import com.nihilus13.domain.model.DetailsResult
import javax.inject.Inject

internal class DetailsReducer @Inject constructor() {

    fun reduceDetails(viewState: DetailsViewState, details: DetailsResult): DetailsViewState =
        when (details) {
            DetailsResult.NoData -> DetailsViewState.Error(viewState.detailsId)
            is DetailsResult.Data -> DetailsViewState.Data(
                detailsId = viewState.detailsId,
                result = details
            )
        }
}
