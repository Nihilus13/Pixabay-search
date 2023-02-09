package com.nihilus13.imageloader

import android.widget.ImageView

interface ImageLoaderManager {

    fun loadImage(imageView: ImageView, imageUrl: String)
}
