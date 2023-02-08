package com.nihilus13.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class DetailsResult : Parcelable {

    @Parcelize
    data class Data(val data: HitData) : DetailsResult()

    @Parcelize
    object NoData : DetailsResult()
}