package com.nihilus13.imageloader.transformation

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.core.graphics.applyCanvas
import coil.bitmappool.BitmapPool
import coil.decode.DecodeUtils
import coil.size.OriginalSize
import coil.size.PixelSize
import coil.size.Scale
import coil.size.Size
import coil.transform.Transformation
import kotlin.math.roundToInt


class RoundedCornerStrokeTransformation(
    @Px private val radius: Float,
    @Px private val strokeWidth: Float = 0f,
    @ColorInt private val colorStroke: Int
) :
    Transformation {

    init {
        require(strokeWidth >= 0) { "Stroke width must be >= 0." }
    }

    override fun key() = "${RoundedCornerStrokeTransformation::class.java.name}-$strokeWidth"

    override suspend fun transform(pool: BitmapPool, input: Bitmap, size: Size): Bitmap {
        val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        strokePaint.color = colorStroke
        strokePaint.style = Paint.Style.STROKE
        strokePaint.strokeWidth = strokeWidth

        val outputWidth: Int
        val outputHeight: Int
        when (size) {
            is PixelSize -> {
                val multiplier = DecodeUtils.computeSizeMultiplier(
                    srcWidth = input.width,
                    srcHeight = input.height,
                    destWidth = size.width,
                    destHeight = size.height,
                    scale = Scale.FILL
                )
                outputWidth = (size.width / multiplier).roundToInt()
                outputHeight = (size.height / multiplier).roundToInt()
            }
            is OriginalSize -> {
                outputWidth = input.width
                outputHeight = input.height
            }
        }
        val output = pool.get(outputWidth, outputHeight, input.config)
        output.applyCanvas {
            val radii = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
            val rect = RectF(0f, 0f, width.toFloat(), height.toFloat())
            val path = Path().apply { addRoundRect(rect, radii, Path.Direction.CW) }
            drawPath(path, strokePaint)
        }
        pool.put(input)


        return output
    }
}
