package com.nihilus13.data

import com.nihilus13.data.db.entity.HitEntity
import java.util.Date

internal object TestDataProvider {

    const val HIT_ID = "1"
    val createdAt = Date(167584000L)
    val hitEntity1 = HitEntity(
        id = HIT_ID,
        createdAt = createdAt.time,
        thumbnailUrl = "https://toppng.com/uploads/preview/sheep-png-images-11553734775mqnnvg1xw7.png",
        largeImageUrl = "https://toppng.com/uploads/preview/sheep-png-images-11553734775mqnnvg1xw7.png",
        tags = "awesome, beauty, sun",
        user = "Hugo",
        likes = 5L,
        downloads = 3L,
        comments = 10L
    )
}