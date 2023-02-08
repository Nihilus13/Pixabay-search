package com.nihilus13.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class SearchResult : Parcelable {

    @Parcelize
    data class Data(val record: SearchRecord) : SearchResult()

    @Parcelize
    object NoData : SearchResult()
}
