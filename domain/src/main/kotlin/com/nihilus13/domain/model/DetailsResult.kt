package com.nihilus13.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsResult(
    val id: String,
    val largeImageURL: String,
    val user: String,
    val tags: List<String>,
    val likes: Long,
    val downloads: Long,
    val comments: Long
) : Parcelable
