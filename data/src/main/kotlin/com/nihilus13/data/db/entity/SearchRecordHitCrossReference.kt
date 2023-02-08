package com.nihilus13.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.nihilus13.data.db.Contract

@Entity(primaryKeys = [Contract.HitTable.HIT_ID, Contract.SearchRecordTable.SEARCH_TEXT])
internal data class SearchRecordHitCrossReference(
    @ColumnInfo(name = Contract.HitTable.HIT_ID)
    val hitId: String,
    @ColumnInfo(name = Contract.SearchRecordTable.SEARCH_TEXT, index = true)
    val searchText: String,
)

internal data class SearchRecordWithHits(
    @Embedded val searchRecord: SearchRecordEntity,
    @Relation(
        parentColumn = Contract.SearchRecordTable.SEARCH_TEXT,
        entityColumn = Contract.HitTable.HIT_ID,
        associateBy = Junction(SearchRecordHitCrossReference::class)
    )
    val hits: List<HitEntity>
)
