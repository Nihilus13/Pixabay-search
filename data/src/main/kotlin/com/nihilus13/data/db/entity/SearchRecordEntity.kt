package com.nihilus13.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nihilus13.data.db.Contract

@Entity(tableName = Contract.SearchRecordTable.TABLE_NAME)
internal data class SearchRecordEntity(
    @PrimaryKey
    @ColumnInfo(name = Contract.Common.ID)
    val id: String,
    @ColumnInfo(name = Contract.Common.CREATED_AT)
    val timestamp: Long,
    @ColumnInfo(name = Contract.SearchRecordTable.SEARCH_TEXT)
    val searchText: String,
    @ColumnInfo(name = Contract.SearchRecordTable.TOTAL)
    val total: Long,
    @ColumnInfo(name = Contract.SearchRecordTable.TOTAL_HITS)
    val totalHits: Long
)