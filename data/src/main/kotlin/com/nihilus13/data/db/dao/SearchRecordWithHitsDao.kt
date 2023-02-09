package com.nihilus13.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.nihilus13.data.db.Contract
import com.nihilus13.data.db.entity.SearchRecordEntity
import com.nihilus13.data.db.entity.SearchRecordHitEntity
import com.nihilus13.data.db.entity.SearchRecordWithHits

@Dao
internal interface SearchRecordWithHitsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchRecord(searchRecord: SearchRecordEntity)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchRecordWithHits(records: List<SearchRecordHitEntity>)

    @Transaction
    @Query(
        "SELECT * FROM ${Contract.SearchRecordTable.TABLE_NAME} " +
                "WHERE ${Contract.SearchRecordTable.SEARCH_TEXT} == :searchText COLLATE NOCASE " +
                "LIMIT 1;"
    )
    suspend fun getSearchRecord(searchText: String): SearchRecordWithHits?
}
