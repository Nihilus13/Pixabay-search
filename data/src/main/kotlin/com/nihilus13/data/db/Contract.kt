package com.nihilus13.data.db

internal object Contract {

    object Common {
        const val CREATED_AT = "created_at"
    }

    object HitTable {
        const val TABLE_NAME = "hit_table"

        const val HIT_ID = "hit_id"
        const val THUMBNAIL_URL = "thumbnail_url"
        const val LARGE_IMAGE_URL = "large_image_url"
        const val TAGS = "tags"
        const val USER = "user"
        const val DOWNLOADS = "downloads"
        const val LIKES = "likes"
        const val COMMENTS = "comments"
    }

    object SearchRecordTable {
        const val TABLE_NAME = "search_record_table"

        const val SEARCH_TEXT = "search_text"
        const val TOTAL = "total"
        const val TOTAL_HITS = "total_hits"
    }

    object SearchRecordHitTable {
        const val TABLE_NAME = "search_record_hit_table"
    }
}
