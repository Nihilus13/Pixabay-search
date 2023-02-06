package com.nihilus13.data.repository.mapper

import com.nihilus13.data.model.SearchResponse
import com.nihilus13.domain.model.SearchResult
import javax.inject.Inject

internal class DomainMapper @Inject constructor() {

    fun mapResponse(response: SearchResponse): List<SearchResult> =
        response.hits.map {
            SearchResult(
                id = it.id,
                thumbnailUrl = it.previewURL,
                pixabayUserName = it.user,
                tags = it.tags.split(","),
                user = it.user,
                downloads = it.downloads,
                likes = it.likes,
                comments = it.comments,
            )
        }
}
