package com.example.vktest.customClocksGroup

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import com.example.vktest.R
import kotlin.math.cos
import kotlin.math.sin


class VKCustomClocksHand @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : VKCustomClocksElement(context, attrs, defStyleAttr) {

    var handColor = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value) {
            if (field != value) {
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
            field = value.coerceIn(1..100)
            findHandPoints()
            invalidate()

        }
    var handWidth = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value) {
            field = value.coerceIn(1..100)
            val realWidth = field.toFloat() / HUNDRED_PERCENT * maxStrokeWidth
            paint.strokeWidth =
                realWidth
            paint.setShadowLayer(
                realWidth * 3,
                HAND_SHADOW_PADDING,
                HAND_SHADOW_PADDING, handColor
            )
            invalidate()
        }

    var maxStrokeWidth = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value) {
            field = value
            handWidth = handWidth
        }

    var currentTime = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value) {
            if (field != value) {
                field = value
                findHandPoints()
                invalidate()
            }
        }

    private var startPointF = PointF()
    private var finishPointF = PointF()

    private var type = HandType.Undefined

    private var degreesPerStep = VKCustomClocksViewGroup.NOT_INITIALIZED_FLOAT

    init {
        if(attrs != null){
            initAttributes(attrs, defStyleAttr, 0)
        } else{
            throw Exception("Hand type is missing")
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

        degreesPerStep = when (type) {
            HandType.SecondHand -> {
                DEGREES_PER_SECOND
            }
            HandType.MinuteHand -> {
                DEGREES_PER_MINUTE
            }
            HandType.HourHand -> {
                DEGREES_PER_HOUR
            }
            else -> {
                throw Exception("Undefined hand type")
            }
        }

        typedArray.recycle()
    }

    override fun resetValues() {
        radius *= PADDING_FOR_HANDS_ENDS
        maxStrokeWidth = (radius / MAX_STROKE_WIDTH_DELIMITER).toInt()
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
        val finishCosAngle = findCos(currentTime * degreesPerStep)
        val finishSinAngle = findSin(currentTime * degreesPerStep)
        startPointF.x = center.x
        startPointF.y = center.y
        finishPointF.x =
            center.x + (handLength / HUNDRED_PERCENT * radius) * finishCosAngle
        finishPointF.y =
            center.y + (handLength / HUNDRED_PERCENT * radius) * finishSinAngle
    }

    private fun findCos(angel: Float): Float {
        return cos(Math.toRadians(angel - STANDARD_ANGEL_TO_ZERO)).toFloat()
    }

    private fun findSin(angel: Float): Float {
        return sin(Math.toRadians(angel - STANDARD_ANGEL_TO_ZERO)).toFloat()
    }

    companion object {
        enum class HandType {
            Undefined, SecondHand, MinuteHand, HourHand
        }
    }
}