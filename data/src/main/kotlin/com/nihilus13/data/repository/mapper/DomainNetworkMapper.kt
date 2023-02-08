package com.nihilus13.data.repository.mapper

import com.nihilus13.data.model.SearchResponse
import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.HitData
import com.nihilus13.domain.model.SearchRecord
import com.nihilus13.domain.model.SearchResult
import javax.inject.Inject

internal class DomainNetworkMapper @Inject constructor() {

    fun mapSearchResponse(response: SearchResponse): DetailsResult.Data =
        mapHitInternal(response)
            .first()
            .let { DetailsResult.Data(it) }

    fun mapSearchResponse(searchText: String, response: SearchResponse): SearchResult.Data =
        mapHitInternal(response)
            .let {
                SearchRecord(
                    searchText = searchText,
                    total = response.total,
                    totalHits = response.totalHits,
                    hits = it
                )
            }.let { SearchResult.Data(it) }


    private fun mapHitInternal(response: SearchResponse): List<HitData> =
        response.hits.map {
            HitData(
                id = it.id.toString(),
                thumbnailUrl = it.previewURL,
                largeImageUrl = it.largeImageURL,
                user = it.user,
                tags = it.tags,
                downloads = it.downloads,
                likes = it.likes,
                comments = it.comments,
            )
        }
}
