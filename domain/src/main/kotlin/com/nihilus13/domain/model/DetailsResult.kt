package com.nihilus13.domain.model

data class DetailsResult(
    val id: String,
    val largeImageURL: String,
    val pixabayUserName: String,
    val tags: List<String>,
    val likes: Long,
    val downloads: Long,
    val comments: Long
)
