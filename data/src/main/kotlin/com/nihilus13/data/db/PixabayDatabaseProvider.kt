package com.nihilus13.data.db

import android.content.Context
import androidx.room.Room

internal object PixabayDatabaseProvider {

    private const val DB_NAME = "pixabay_db"

    @Volatile
    private lateinit var db: PixabayDatabase

    fun getDatabase(context: Context): PixabayDatabase {
        if (!::db.isInitialized) {
            synchronized(this) {
                db = buildDatabase(context)
            }
        }
        return db
    }

    private fun buildDatabase(context: Context): PixabayDatabase =
        Room.databaseBuilder(
            context,
            PixabayDatabase::class.java,
            context.getDatabasePath(DB_NAME).absolutePath
        ).build()
}
