package com.example.vktest

import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlin.math.cos
import kotlin.math.sin


class VKCustomClocksHand @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var handColor = NOT_INITIALIZED
    var handLength = NOT_INITIALIZED
    var handWidth = NOT_INITIALIZED
    var currentTime = NOT_INITIALIZED

    private var startPointF = PointF()
    private var finishPointF = PointF()

    private var center = PointF(NOT_INITIALIZED_FLOAT, NOT_INITIALIZED_FLOAT)
    private var radius = NOT_INITIALIZED_FLOAT
    private var degreesPerStep = NOT_INITIALIZED

    private fun findHandPoints(
        time: Int,
        degrees: Float,
        length: Int,
        points: Pair<PointF, PointF>
    ) {
        val startCosAngle = findCos(time * degrees + HALF_CIRCLE_DEGREES)
        val startSinAngle = findSin(time * degrees + HALF_CIRCLE_DEGREES)
        val finishCosAngle = findCos(time * degrees)
        val finishSinAngle = findSin(time * degrees)
        points.first.x = center.x + (0.1f * radius) * startCosAngle
        points.first.y = center.y + (0.1f * radius) * startSinAngle
        points.second.x = center.x + (length / HUNDRED_PERCENT * radius) * finishCosAngle
        points.second.y = center.y + (length / HUNDRED_PERCENT * radius) * finishSinAngle
    }

    private fun findCos(angel: Float): Float {
        return cos(Math.toRadians(angel - STANDARD_ANGEL_TO_ZERO)).toFloat()
    }

    private fun findSin(angel: Float): Float {
        return sin(Math.toRadians(angel - STANDARD_ANGEL_TO_ZERO)).toFloat()
    }


    companion object{
        enum class HandType{
            SecondHand, MinuteHand, HourHand
        }
        private const val NOT_INITIALIZED = 0
        private const val NOT_INITIALIZED_FLOAT = 0f

        private const val STANDARD_ANGEL_TO_ZERO = 90.0

        private const val HUNDRED_PERCENT = 100f
        private const val CIRCLE_DEGREES_FLOAT = 360f
        private const val HALF_CIRCLE_DEGREES = 180
    }
}