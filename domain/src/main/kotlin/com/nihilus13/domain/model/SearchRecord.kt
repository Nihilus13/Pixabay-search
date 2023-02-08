package com.nihilus13.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchRecord(
    val searchText: String,
    val total: Long,
    val totalHits: Long,
    val hits: List<HitData>
) : Parcelable
