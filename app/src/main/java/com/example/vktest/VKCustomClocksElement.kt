package com.example.vktest

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

    protected var center = PointF(VKCustomClocksViewGroup.NOT_INITIALIZED_FLOAT, VKCustomClocksViewGroup.NOT_INITIALIZED_FLOAT)
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
        val safeRadius = Math.min(safeHeight, safeWidth) / 2f

        radius = safeRadius * VKCustomClocksCircle.PADDING_FROM_BORDERS
        center.x = paddingLeft + safeWidth / 2f
        center.y = paddingTop + safeHeight / 2f

        resetValues()
    }

    protected abstract fun resetValues()

    companion object{

        protected const val CIRCLE_SHADOW_PADDING = 10f
        protected const val HAND_SHADOW_PADDING = 5f

        protected const val PADDING_FROM_PARENT_RADIUS = 0.9f
        protected const val PADDING_FROM_BORDERS = 0.9f

        protected const val PADDING_FOR_NUMBERS = 0.7f
        protected const val PADDING_FOR_POINTS = 0.82f

        protected const val FIRST_INDEX = 0

        protected const val POINT_DELIMITERS_COUNT = 60
        protected const val NUMBER_DELIMITERS_COUNT = 12

        protected const val DEFAULT_STROKE_WIDTH = 10f
        protected const val DEFAULT_TEXT_SIZE = 10f

    }

}