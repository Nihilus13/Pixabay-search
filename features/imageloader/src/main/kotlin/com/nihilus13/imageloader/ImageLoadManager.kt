package com.nihilus13.imageloader

import android.widget.ImageView

interface ImageLoadManager {
    fun loadImage(
        imageView: ImageView,
        imageUrl: String,
        transformationType: TransformationType? = null
    )
}
