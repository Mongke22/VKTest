package com.example.vktest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

class VKCustomClocksCircle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var center = PointF(VKCustomClocksViewGroup.NOT_INITIALIZED_FLOAT, VKCustomClocksViewGroup.NOT_INITIALIZED_FLOAT)
    private var radius = VKCustomClocksViewGroup.NOT_INITIALIZED_FLOAT

    var maxStrokeWidth = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value){
            field = value
            circleWidth = circleWidth
        }


    var circleColor = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value){
            field = value
            circlePaint.color = value
            circlePaint.setShadowLayer(circleWidth.toFloat(),
                CIRCLE_SHADOW_PADDING,
                CIRCLE_SHADOW_PADDING, value)
            invalidate()
        }
    var backgroundCircleColor = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value){
            field = value
            backGroundPaint.color = value
            invalidate()
        }
    var circleWidth = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value){
            field = if (value in 1..100) {
                value
            } else {
                Math.min(Math.max(value, 1), 100)
            }
            circlePaint.strokeWidth = field.toFloat() / VKCustomClocksViewGroup.HUNDRED_PERCENT * maxStrokeWidth
            circlePaint.setShadowLayer(circleWidth.toFloat(),
                CIRCLE_SHADOW_PADDING,
                CIRCLE_SHADOW_PADDING, circleColor)
            invalidate()
        }

    private var circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }
    private var backGroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val safeWidth = w - paddingLeft - paddingRight
        val safeHeight = h - paddingTop - paddingBottom
        val safeRadius = Math.min(safeHeight, safeWidth) / 2f

        radius = safeRadius * PADDING_FROM_BORDERS
        center.x = paddingLeft + safeWidth / 2f
        center.y = paddingTop + safeHeight / 2f

        resetValues()
    }
    private fun resetValues(){
        maxStrokeWidth = (radius / VKCustomClocksViewGroup.MAX_STROKE_WIDTH_DELIMITER).toInt()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBackGround(canvas)
        drawMainCircle(canvas)
    }

    private fun drawBackGround(canvas: Canvas){
        canvas.drawCircle(center.x, center.y, radius, backGroundPaint)
    }

    private fun drawMainCircle(canvas: Canvas){
        canvas.drawCircle(center.x, center.y, radius, circlePaint)
    }

    companion object{
    }
}