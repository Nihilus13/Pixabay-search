package com.nihilus13.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.nihilus13.data.db.Contract
import com.nihilus13.data.db.entity.HitEntity

@Dao
internal interface HitDao {

    @Query(
        "SELECT * FROM ${Contract.HitTable.TABLE_NAME} " +
                "WHERE ${Contract.Common.ID} = :id " +
                "LIMIT 1;"
    )
    suspend fun getHit(id: String): HitEntity
}