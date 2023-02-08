package com.nihilus13.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nihilus13.data.db.Contract

@Entity(tableName = Contract.HitTable.TABLE_NAME)
internal data class HitEntity(
    @PrimaryKey
    @ColumnInfo(name = Contract.HitTable.HIT_ID)
    val id: String,
    @ColumnInfo(name = Contract.Common.CREATED_AT)
    val createdAt: Long,
    @ColumnInfo(name = Contract.HitTable.THUMBNAIL_URL)
    val thumbnailUrl: String,
    @ColumnInfo(name = Contract.HitTable.LARGE_IMAGE_URL)
    val largeImageUrl: String,
    @ColumnInfo(name = Contract.HitTable.TAGS)
    val tags: String,
    @ColumnInfo(name = Contract.HitTable.USER)
    val user: String,
    @ColumnInfo(name = Contract.HitTable.DOWNLOADS)
    val downloads: Long,
    @ColumnInfo(name = Contract.HitTable.LIKES)
    val likes: Long,
    @ColumnInfo(name = Contract.HitTable.COMMENTS)
    val comments: Long
)
