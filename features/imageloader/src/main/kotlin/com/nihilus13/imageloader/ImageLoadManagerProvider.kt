package com.nihilus13.imageloader

import android.content.Context

object ImageLoadManagerProvider {

    fun createImageLoaderManager(context: Context): ImageLoaderManager =
        ImageLoaderManagerManagerImpl(context)
}
