package com.nihilus13.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.nihilus13.data.db.Contract

@Entity(primaryKeys = [Contract.HitTable.HIT_ID, Contract.SearchRecordTable.SEARCH_RECORD_ID])
internal data class SearchRecordHitCrossReference(
    @ColumnInfo(name = Contract.HitTable.HIT_ID)
    val hitId: String,
    @ColumnInfo(name = Contract.SearchRecordTable.SEARCH_RECORD_ID)
    val searchId: String,
)

internal data class SearchRecordWithHits(
    @Embedded val searchRecordEntity: SearchRecordEntity,
    @Relation(
        parentColumn = Contract.SearchRecordTable.SEARCH_RECORD_ID,
        entityColumn = Contract.HitTable.HIT_ID,
        associateBy = Junction(SearchRecordHitCrossReference::class)
    )
    val hits: List<HitEntity>
)