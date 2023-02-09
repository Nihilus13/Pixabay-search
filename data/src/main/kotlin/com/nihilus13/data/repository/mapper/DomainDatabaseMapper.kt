package com.nihilus13.data.repository.mapper

import com.nihilus13.data.date.DateSource
import com.nihilus13.data.db.entity.HitEntity
import com.nihilus13.data.db.entity.SearchRecordEntity
import com.nihilus13.data.db.entity.SearchRecordWithHits
import com.nihilus13.domain.model.HitData
import com.nihilus13.domain.model.SearchRecord
import javax.inject.Inject

internal class DomainDatabaseMapper @Inject constructor(private val dateSource: DateSource) {

    fun mapHitEntity(hits: HitEntity): HitData =
        mapHitEntitiesInternal(listOf(hits)).first()

    fun mapHitSearchRecord(searchRecord: SearchRecord): SearchRecordWithHits {
        val createdAtTimestamp = dateSource.getCurrentDateTimestamp()
        val searchRecordEntity = SearchRecordEntity(
            searchText = searchRecord.searchText,
            createdAt = createdAtTimestamp,
            total = searchRecord.total,
            totalHits = searchRecord.totalHits
        )
        val hits = searchRecord.hits.map { it.map(createdAtTimestamp) }
        return SearchRecordWithHits(
            searchRecord = searchRecordEntity,
            hits = hits
        )
    }

    fun mapHitEntities(hits: List<HitEntity>): List<HitData> =
        mapHitEntitiesInternal(hits)

    private fun HitEntity.map(): HitData =
        HitData(
            id = id,
            thumbnailUrl = thumbnailUrl,
            largeImageUrl = largeImageUrl,
            user = user,
            tags = tags,
            downloads = downloads,
            likes = likes,
            comments = comments
        )

    fun mapHitData(hit: HitData): HitEntity = mapHitDataInternal(listOf(hit)).first()

    fun mapHitData(hits: List<HitData>): List<HitEntity> = mapHitDataInternal(hits)

    private fun mapHitDataInternal(hits: List<HitData>): List<HitEntity> {
        val createdAtTimestamp = dateSource.getCurrentDateTimestamp()
        return hits.map { it.map(createdAtTimestamp) }
    }

    private fun HitData.map(createdAt: Long): HitEntity =
        HitEntity(
            id = id,
            createdAt = createdAt,
            thumbnailUrl = thumbnailUrl,
            largeImageUrl = largeImageUrl,
            user = user,
            tags = tags,
            downloads = downloads,
            likes = likes,
            comments = comments
        )

    fun mapSearchRecord(result: SearchRecordWithHits): SearchRecord {
        val hits = mapHitEntitiesInternal(result.hits)
        return SearchRecord(
            searchText = result.searchRecord.searchText,
            total = result.searchRecord.total,
            totalHits = result.searchRecord.totalHits,
            hits = hits
        )
    }

    private fun mapHitEntitiesInternal(hits: List<HitEntity>): List<HitData> =
        hits.map { it.map() }
}
