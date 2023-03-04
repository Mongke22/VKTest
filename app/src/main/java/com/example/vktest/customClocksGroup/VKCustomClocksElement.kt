package com.example.vktest.customClocksGroup

import android.content.Context
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

abstract class VKCustomClocksElement @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    protected var center = PointF(
        VKCustomClocksViewGroup.NOT_INITIALIZED_FLOAT,
        VKCustomClocksViewGroup.NOT_INITIALIZED_FLOAT
    )
    protected var radius = VKCustomClocksViewGroup.NOT_INITIALIZED_FLOAT
    protected var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = VKCustomClocksViewGroup.DEFAULT_COLOR
        strokeWidth = DEFAULT_STROKE_WIDTH
        textSize = DEFAULT_TEXT_SIZE
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val safeWidth = w - paddingLeft - paddingRight
        val safeHeight = h - paddingTop - paddingBottom
        val safeRadius = Math.min(safeHeight, safeWidth) * 0.5f

        radius = safeRadius
        center.x = paddingLeft + safeWidth / 2f
        center.y = paddingTop + safeHeight / 2f

        resetValues()
    }

    protected abstract fun resetValues()

    companion object {

        const val CIRCLE_SHADOW_PADDING = 10f
        const val HAND_SHADOW_PADDING = 5f

        const val PADDING_FROM_BORDERS = 0.9f

        const val PADDING_FOR_NUMBERS = PADDING_FROM_BORDERS * 0.7f
        const val PADDING_FOR_POINTS = PADDING_FROM_BORDERS * 0.9f
        const val PADDING_FOR_HANDS_ENDS = PADDING_FOR_POINTS

        const val DEGREES_PER_SECOND = 6f
        const val DEGREES_PER_MINUTE = 6f
        const val DEGREES_PER_HOUR = 30f

        const val FIRST_INDEX = 0
        const val CIRCLE_DEGREES_FLOAT = 360f
        const val STANDARD_ANGEL_TO_ZERO = 90.0

        const val POINT_DELIMITERS_COUNT = 60
        const val NUMBER_DELIMITERS_COUNT = 12

        const val DEFAULT_STROKE_WIDTH = 10f
        const val DEFAULT_TEXT_SIZE = 10f

        const val HUNDRED_PERCENT = 100f

        const val MAX_STROKE_WIDTH_DELIMITER = 10
        const val MAX_NUMBER_SIZE_DELIMITER = 3

    }

}