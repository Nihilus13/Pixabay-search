package com.nihilus13.data.repository.source

import com.nihilus13.data.db.dao.HitDao
import com.nihilus13.data.db.dao.SearchRecordWithHitsDao
import com.nihilus13.data.repository.mapper.DomainDatabaseMapper
import com.nihilus13.domain.model.DetailsResult
import com.nihilus13.domain.model.HitData
import com.nihilus13.domain.model.SearchRecord
import com.nihilus13.domain.model.SearchResult
import com.nihilus13.domain.repository.source.CachedDataSource
import javax.inject.Inject

internal class CachedDataSourceImpl @Inject constructor(
    private val hitDao: HitDao,
    private val searchRecordWithHitsDao: SearchRecordWithHitsDao,
    private val mapper: DomainDatabaseMapper
) : CachedDataSource {

    override suspend fun insertHitData(data: SearchRecord) {
        val result = mapper.mapHitData(data.hits)
        hitDao.insertHits(result)
    }

    override suspend fun insertHitData(data: HitData) {
        val result = mapper.mapHitData(data)
        hitDao.insertHit(result)
    }

    override suspend fun searchForImages(searchText: String): SearchResult {
        val result = searchRecordWithHitsDao.getSearchRecord(searchText)

        return if (result != null) {
            val searchRecord = mapper.mapSearchRecord(result)
            SearchResult.Data(searchRecord)
        } else {
            SearchResult.NoData
        }
    }

    override suspend fun getDetailedImage(hitId: String): DetailsResult {
        val result = hitDao.getHit(hitId)
        return if (result != null) {
            val hitData = mapper.mapHitEntity(result)
            DetailsResult.Data(hitData)
        } else {
            DetailsResult.NoData
        }
    }
}
