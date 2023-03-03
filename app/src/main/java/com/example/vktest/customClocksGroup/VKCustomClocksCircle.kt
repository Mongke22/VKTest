package com.example.vktest.customClocksGroup

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet

class VKCustomClocksCircle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : VKCustomClocksElement(context, attrs, defStyleAttr) {


    var maxStrokeWidth = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value) {
            field = value
            circleWidth = circleWidth
        }


    var circleColor = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value) {
            field = value
            paint.color = value
            paint.setShadowLayer(
                circleWidth.toFloat(),
                CIRCLE_SHADOW_PADDING,
                CIRCLE_SHADOW_PADDING, value
            )
            invalidate()
        }
    var backgroundCircleColor = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value) {
            field = value
            backGroundPaint.color = value
            invalidate()
        }
    var circleWidth = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value) {
            field = if (value in 1..100) {
                value
            } else {
                Math.min(Math.max(value, 1), 100)
            }
            paint.strokeWidth =
                field.toFloat() / HUNDRED_PERCENT * maxStrokeWidth
            paint.setShadowLayer(
                circleWidth.toFloat(),
                CIRCLE_SHADOW_PADDING,
                CIRCLE_SHADOW_PADDING, circleColor
            )
            invalidate()
        }

    private var backGroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    override fun resetValues() {
        radius *= PADDING_FROM_BORDERS
        maxStrokeWidth = (radius / MAX_STROKE_WIDTH_DELIMITER).toInt()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBackGround(canvas)
        drawMainCircle(canvas)
    }

    private fun drawBackGround(canvas: Canvas) {
        canvas.drawCircle(center.x, center.y, radius, backGroundPaint)
    }

    private fun drawMainCircle(canvas: Canvas) {
        canvas.drawCircle(center.x, center.y, radius, paint)
    }

    companion object {
    }
}