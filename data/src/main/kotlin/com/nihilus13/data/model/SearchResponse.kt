package com.nihilus13.data.model

internal data class SearchResponse(
    val total: Long,
    val totalHits: Long,
    val hits: List<Hit>
)

internal data class Hit(
    val id: Long,
    val previewURL: String,
    val tags: String,
    val largeImageURL: String,
    val user: String,
    val downloads: Long,
    val likes: Long,
    val comments: Long
)
