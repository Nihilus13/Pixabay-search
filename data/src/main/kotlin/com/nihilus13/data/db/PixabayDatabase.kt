package com.nihilus13.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nihilus13.data.db.PixabayDatabase.Companion.DB_VERSION
import com.nihilus13.data.db.dao.HitDao
import com.nihilus13.data.db.dao.SearchRecordWithHitsDao
import com.nihilus13.data.db.entity.HitEntity
import com.nihilus13.data.db.entity.SearchRecordEntity
import com.nihilus13.data.db.entity.SearchRecordHitCrossReference

@Database(
    entities = [
        SearchRecordEntity::class,
        HitEntity::class,
        SearchRecordHitCrossReference::class
    ],
    version = DB_VERSION,
    exportSchema = false
)
internal abstract class PixabayDatabase : RoomDatabase() {

    abstract fun searchRecordWithHitsDao(): SearchRecordWithHitsDao
    abstract fun hitDao(): HitDao

    companion object {
        const val DB_VERSION = 1
    }
}
