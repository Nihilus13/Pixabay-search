package com.nihilus13.imageloader

import android.content.Context
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.load
import coil.memory.MemoryCache

internal class ImageLoaderManagerManagerImpl internal constructor(context: Context) :
    ImageLoaderManager {

    private val imageLoader = ImageLoader.Builder(context)
        .memoryCache {
            MemoryCache.Builder(context)
                .maxSizePercent(MEMORY_CACHE)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(context.cacheDir.resolve(IMAGE_CACHE))
                .maxSizePercent(DISK_CACHE)
                .build()
        }
        .components { add(SvgDecoder.Factory()) }
        .build()

    override fun loadImage(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl, imageLoader)
    }

    private companion object {
        const val IMAGE_CACHE = "image_cache"
        const val MEMORY_CACHE = 0.25
        const val DISK_CACHE = 0.02
    }
}
