package com.nihilus13.imageloader

import android.content.Context
import android.widget.ImageView
import coil.ImageLoader
import coil.ImageLoaderBuilder
import coil.api.load
import coil.decode.SvgDecoder
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import com.nihilus13.imageloader.TransformationType.CircleCrop
import com.nihilus13.imageloader.TransformationType.RoundedCorners
import com.nihilus13.imageloader.TransformationType.Stroke
import com.nihilus13.imageloader.transformation.RoundedCornerStrokeTransformation

internal class ImageLoaderManagerImpl internal constructor(context: Context) : ImageLoadManager {

    private val imageLoader: ImageLoader

    init {
        imageLoader = ImageLoaderBuilder(context)
            .componentRegistry {
                add(SvgDecoder(context))
            }.build()
    }

    override fun loadImage(
        imageView: ImageView,
        imageUrl: String,
        transformationType: TransformationType?
    ) {
        imageView.load(imageUrl, imageLoader) {
            transformationType?.let { transformations(it.getTransformation()) }
        }
    }

    private fun TransformationType.getTransformation(): Transformation =
        when (this) {
            is CircleCrop -> CircleCropTransformation()
            is RoundedCorners -> {
                RoundedCornersTransformation(
                    topLeftRadiusInPx,
                    topRightRadiusInPx,
                    bottomLeftRadiusInPx,
                    bottomRightRadiusInPx
                )
            }
            is Stroke -> RoundedCornerStrokeTransformation(
                radius,
                strokeWidth,
                strokeColor
            )
        }
}
