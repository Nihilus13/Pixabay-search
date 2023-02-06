package com.nihilus13.domain.repository

import com.nihilus13.domain.model.SearchResult

interface SearchRepository {
    suspend fun searchForImages(searchText: String): List<SearchResult>
}