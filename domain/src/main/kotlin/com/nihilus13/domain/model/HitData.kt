package com.nihilus13.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HitData(
    val id: String,
    val thumbnailUrl: String,
    val largeImageUrl: String,
    val user: String,
    val tags: String,
    val downloads: Long,
    val likes: Long,
    val comments: Long
) : Parcelable
