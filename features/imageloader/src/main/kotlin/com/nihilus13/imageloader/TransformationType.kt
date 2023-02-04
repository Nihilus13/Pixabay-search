package com.nihilus13.imageloader

import androidx.annotation.ColorInt
import androidx.annotation.Px

sealed class TransformationType {

    object CircleCrop : TransformationType()

    class RoundedCorners(
        val topLeftRadiusInPx: Float,
        val topRightRadiusInPx: Float,
        val bottomLeftRadiusInPx: Float,
        val bottomRightRadiusInPx: Float
    ) : TransformationType() {
        constructor(radiusInPx: Float) : this(radiusInPx, radiusInPx, radiusInPx, radiusInPx)
    }

    class Stroke(
        @Px val radius: Float,
        @Px val strokeWidth: Float,
        @ColorInt val strokeColor: Int
    ) : TransformationType()
}
