package com.example.vktest.customClocksGroup

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.example.vktest.R
import kotlin.math.cos
import kotlin.math.sin

class VKCustomClocksDelimiters @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : VKCustomClocksElement(context, attrs, defStyleAttr) {


    private var type = DelimiterType.Undefined

    private var paddingFromMainCircle = VKCustomClocksViewGroup.NOT_INITIALIZED_FLOAT

    private var delimitersCount = VKCustomClocksViewGroup.NOT_INITIALIZED
    private var delimiterCoordinates = ArrayList<PointF>()

    private var maxStrokeWidth = 50
        set(value) {
            field = value
            delimiterWidth = delimiterWidth
        }
    private var maxNumberSize = 50f
        set(value) {
            field = value
            delimiterSize = delimiterSize
        }

    var delimiterColor = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value) {
            field = value
            paint.color = value
            invalidate()
        }

    var delimiterSize = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value) {
            field = if (value in 1..100) {
                value
            } else {
                Math.min(Math.max(value, 1), 100)
            }
            paint.textSize =
                field.toFloat() / VKCustomClocksViewGroup.HUNDRED_PERCENT * maxNumberSize
            invalidate()
        }

    var delimiterWidth = VKCustomClocksViewGroup.NOT_INITIALIZED
        set(value) {
            field = if (value in 1..100) {
                value
            } else {
                Math.min(Math.max(value, 1), 100)
            }
            paint.strokeWidth =
                field.toFloat() / VKCustomClocksViewGroup.HUNDRED_PERCENT * maxStrokeWidth
            invalidate()
        }

    init {
        initAttributes(attrs!!, defStyleAttr, 0)
    }

    private fun initAttributes(attributeSet: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.VKCustomClocksDelimiters,
            defStyleAttr,
            defStyleRes
        )
        type = DelimiterType.values()[typedArray.getInt(
            R.styleable.VKCustomClocksDelimiters_delimiterType,
            DelimiterType.Undefined.ordinal
        )]

        when (type) {
            DelimiterType.PointDelimiter -> {
                delimitersCount = POINT_DELIMITERS_COUNT
                paddingFromMainCircle = PADDING_FOR_POINTS
            }
            DelimiterType.NumberDelimiter -> {
                delimitersCount = NUMBER_DELIMITERS_COUNT
                paddingFromMainCircle = PADDING_FOR_NUMBERS
            }
            else -> {
                throw Exception("Undefined delimiter type")
            }
        }

        typedArray.recycle()
    }

    override fun resetValues() {
        radius *= paddingFromMainCircle
        maxNumberSize = radius / VKCustomClocksViewGroup.MAX_NUMBER_SIZE_DELIMITER
        maxStrokeWidth = (radius / VKCustomClocksViewGroup.MAX_STROKE_WIDTH_DELIMITER).toInt()

        delimiterCoordinates = computeExtraCirclePoints()
    }


    private fun computeExtraCirclePoints(
    ): ArrayList<PointF> {
        val angle = VKCustomClocksViewGroup.CIRCLE_DEGREES_FLOAT / delimitersCount
        val result = ArrayList<PointF>()
        for (indicator in 0 until delimitersCount) {
            val point = PointF()
            val cosAngle =
                cos(Math.toRadians(indicator * angle - VKCustomClocksViewGroup.STANDARD_ANGEL_TO_ZERO)).toFloat()
            val sinAngle =
                sin(Math.toRadians(indicator * angle - VKCustomClocksViewGroup.STANDARD_ANGEL_TO_ZERO)).toFloat()
            point.x = center.x + radius * cosAngle
            point.y = center.y + radius * sinAngle
            result.add(point)
        }
        return result
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        when (type) {
            DelimiterType.PointDelimiter -> {
                drawPoints(canvas)
            }
            DelimiterType.NumberDelimiter -> {
                drawNumbers(canvas)
            }
            else -> {
                throw Exception("Undefined delimiter type on drawing")
            }
        }
    }

    private fun drawPoints(canvas: Canvas) {
        for (index in delimiterCoordinates.indices) {
            if (index % 5 == 0) {
                paint.color = delimiterColor
            } else {
                paint.color = Color.GRAY
            }
            canvas.drawPoint(
                delimiterCoordinates[index].x,
                delimiterCoordinates[index].y,
                paint
            )
        }
    }

    private fun drawNumbers(canvas: Canvas) {
        var hours = 12
        val numberRect = Rect()
        for (point in delimiterCoordinates) {
            paint.getTextBounds(
                hours.toString(),
                FIRST_INDEX, hours.toString().length, numberRect
            )
            canvas.drawText(
                hours.toString(),
                point.x - (numberRect.width() / 2),
                point.y + (numberRect.height() / 2),
                paint
            )
            if (hours == 12) {
                hours = 1
            } else {
                hours++
            }
        }
    }


    companion object {
        enum class DelimiterType {
            Undefined, NumberDelimiter, PointDelimiter
        }

    }
}