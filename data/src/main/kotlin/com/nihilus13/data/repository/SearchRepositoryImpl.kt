package com.nihilus13.data.repository

import com.nihilus13.domain.model.SearchResult
import com.nihilus13.domain.repository.SearchRepository
import com.nihilus13.domain.repository.source.CachedDataSource
import com.nihilus13.domain.repository.source.RemoteDataSource
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val cachedDataSource: CachedDataSource,
    private val remoteDataSource: RemoteDataSource
) : SearchRepository {

    override suspend fun searchForImages(searchText: String): List<SearchResult> {
        TODO("Not yet implemented")
    }
}
