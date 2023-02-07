package com.nihilus13.pixabay.fragment.details.state

import android.os.Parcelable
import com.nihilus13.domain.model.DetailsResult
import kotlinx.parcelize.Parcelize

internal sealed class DetailsViewState(open val detailsId: String) : Parcelable {

    @Parcelize
    data class Pending(override val detailsId: String) : DetailsViewState(detailsId)

    @Parcelize
    data class Data(override val detailsId: String, val result: DetailsResult) :
        DetailsViewState(detailsId)

    @Parcelize
    data class Error(override val detailsId: String) :
        DetailsViewState(detailsId)
}
