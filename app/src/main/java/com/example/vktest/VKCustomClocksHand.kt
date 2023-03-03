package com.example.vktest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.cos
import kotlin.math.sin


class VKCustomClocksHand @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var handColor = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value) {
            if(field != value) {
                field = value
                paint.color = value
                paint.setShadowLayer(
                    handWidth.toFloat(),
                    HAND_SHADOW_PADDING,
                    HAND_SHADOW_PADDING, value
                )
                invalidate()
            }
        }
    var handLength = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value) {
            field = if (value in 1..100) {
                value
            } else {
                Math.min(Math.max(value, 1), 100)
            }
            findHandPoints()
            invalidate()

        }
    var handWidth = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value) {
            field = if (value in 1..100) {
                value
            } else {
                Math.min(Math.max(value, 1), 100)
            }
            paint.strokeWidth = field.toFloat() / VKCustomClocksViewGroup.HUNDRED_PERCENT * maxStrokeWidth
            paint.setShadowLayer(
                handWidth.toFloat(),
                HAND_SHADOW_PADDING,
                HAND_SHADOW_PADDING, handColor
            )
            invalidate()
        }

    var maxStrokeWidth = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value){
            field = value
            handWidth = handWidth
        }

    var currentTime = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value){
            if(field != value) {
                field = value
                findHandPoints()
                invalidate()
            }
        }

    private var startPointF = PointF()
    private var finishPointF = PointF()

    private var type = HandType.Undefined

    private var center = PointF(VKCustomClocksViewGroup.NOT_INITIALIZED_FLOAT, VKCustomClocksViewGroup.NOT_INITIALIZED_FLOAT)
    private var radius = VKCustomClocksViewGroup.NOT_INITIALIZED_FLOAT
    private var degreesPerStep = VKCustomClocksViewGroup.NOT_INITIALIZED_FLOAT

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        initAttributes(attrs!!, defStyleAttr, 0)
        paint.apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeWidth = handWidth / VKCustomClocksViewGroup.HUNDRED_PERCENT * maxStrokeWidth
            setShadowLayer(
                handWidth.toFloat(),
                HAND_SHADOW_PADDING,
                HAND_SHADOW_PADDING, Color.RED
            )
        }
    }

    private fun initAttributes(attributeSet: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.VKCustomClocksHand,
            defStyleAttr,
            defStyleRes
        )
        type = HandType.values()[typedArray.getInt(
            R.styleable.VKCustomClocksHand_handType,
            HandType.Undefined.ordinal
        )]

        degreesPerStep = when(type){
            HandType.SecondHand -> {
                6f
            }
            HandType.MinuteHand -> {
                6f
            }
            HandType.HourHand -> {
                30f
            }
            else -> {
                throw Exception("Undefined hand type")
            }
        }

        typedArray.recycle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val safeWidth = w - paddingLeft - paddingRight
        val safeHeight = h - paddingTop - paddingBottom
        val safeRadius = Math.min(safeHeight, safeWidth) / 2f

        radius = safeRadius * PADDING_FROM_PARENT_RADIUS
        center.x = paddingLeft + safeWidth / 2f
        center.y = paddingTop + safeHeight / 2f

        resetValues()
    }

    private fun resetValues() {
        maxStrokeWidth = (radius / VKCustomClocksViewGroup.MAX_STROKE_WIDTH_DELIMITER).toInt()
        findHandPoints()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawHand(canvas)
    }

    private fun drawHand(canvas: Canvas) {
        canvas.drawLine(
            startPointF.x,
            startPointF.y,
            finishPointF.x,
            finishPointF.y,
            paint
        )
    }
    private fun findHandPoints(
    ) {
        val startCosAngle = findCos(currentTime * degreesPerStep + VKCustomClocksViewGroup.HALF_CIRCLE_DEGREES)
        val startSinAngle = findSin(currentTime * degreesPerStep + VKCustomClocksViewGroup.HALF_CIRCLE_DEGREES)
        val finishCosAngle = findCos(currentTime * degreesPerStep)
        val finishSinAngle = findSin(currentTime * degreesPerStep)
        startPointF.x = center.x
        startPointF.y = center.y
        finishPointF.x = center.x + (handLength / VKCustomClocksViewGroup.HUNDRED_PERCENT * radius) * finishCosAngle
        finishPointF.y = center.y + (handLength / VKCustomClocksViewGroup.HUNDRED_PERCENT * radius) * finishSinAngle
    }

    private fun findCos(angel: Float): Float {
        return cos(Math.toRadians(angel - VKCustomClocksViewGroup.STANDARD_ANGEL_TO_ZERO)).toFloat()
    }

    private fun findSin(angel: Float): Float {
        return sin(Math.toRadians(angel - VKCustomClocksViewGroup.STANDARD_ANGEL_TO_ZERO)).toFloat()
    }


    companion object {
        enum class HandType {
            Undefined, SecondHand, MinuteHand, HourHand
        }
    }
}