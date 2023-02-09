package com.nihilus13.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.nihilus13.data.db.Contract

@Entity(
    primaryKeys = [Contract.SearchRecordTable.SEARCH_TEXT, Contract.HitTable.HIT_ID],
    tableName = Contract.SearchRecordHitTable.TABLE_NAME
)
internal data class SearchRecordHitEntity(
    @ColumnInfo(name = Contract.SearchRecordTable.SEARCH_TEXT)
    val searchText: String,
    @ColumnInfo(name = Contract.HitTable.HIT_ID)
    val hitId: String
)

internal data class SearchRecordWithHits(
    @Embedded val searchRecord: SearchRecordEntity,
    @Relation(
        parentColumn = Contract.SearchRecordTable.SEARCH_TEXT,
        entityColumn = Contract.HitTable.HIT_ID,
        associateBy = Junction(
            value = SearchRecordHitEntity::class,
            parentColumn = Contract.SearchRecordTable.SEARCH_TEXT,
            entityColumn = Contract.HitTable.HIT_ID
        )
    )
    val hits: List<HitEntity>
)
