package com.nihilus13.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.nihilus13.data.db.Contract
import com.nihilus13.data.db.entity.HitEntity
import com.nihilus13.data.db.entity.SearchRecordEntity
import com.nihilus13.data.db.entity.SearchRecordHitCrossReference
import com.nihilus13.data.db.entity.SearchRecordWithHits

@Dao
internal interface SearchRecordWithHitsDao {

    suspend fun insert(searchRecord: SearchRecordEntity, hits: List<HitEntity>) {
        insertSearchRecord(searchRecord)
        insertHits(hits)
        val crossRef = hits.map { SearchRecordHitCrossReference(searchRecord.id, it.id) }
        insertSearchRecordWithHits(crossRef)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchRecord(searchRecord: SearchRecordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHits(hit: List<HitEntity>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchRecordWithHits(records: List<SearchRecordHitCrossReference>)

    @Transaction
    @Query(
        "SELECT * FROM ${Contract.SearchRecordTable.TABLE_NAME} " +
                "WHERE ${Contract.SearchRecordTable.SEARCH_TEXT} = :searchText " +
                "LIMIT 1;"
    )
    suspend fun getSearchRecord(searchText: String): SearchRecordWithHits
}