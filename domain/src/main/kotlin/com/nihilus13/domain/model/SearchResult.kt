package com.nihilus13.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResult(
    val id: Long,
    val thumbnailUrl: String,
    val pixabayUserName: String,
    val tags: List<String>,
    val user: String,
    val downloads: Long,
    val likes: Long,
    val comments: Long
) : Parcelable
