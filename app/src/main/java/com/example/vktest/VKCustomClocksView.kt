package com.example.vktest

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import java.lang.Math.max
import java.lang.Math.min
import kotlin.concurrent.thread
import kotlin.math.cos
import kotlin.math.sin

class VKCustomClocksView(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : View(context, attributeSet, defStyleAttr, defStyleRes) {

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attributeSet,
        defStyleAttr,
        R.style.DefaultVKClocksStyle
    )

    constructor(context: Context, attributeSet: AttributeSet?) : this(
        context,
        attributeSet,
        R.attr.vkCustomClocksStyle
    )

    constructor(context: Context) : this(context, null)

    private val timer = CurrentTimePicker()
    private var seconds = NOT_INITIALIZED
    private var minutes = NOT_INITIALIZED
    private var hours = NOT_INITIALIZED

    //Настройки секундной стрелки
     var secondHandColor = NOT_INITIALIZED
        set(value) {
            field = value
            secondHandPaint.color = value
            secondHandPaint.setShadowLayer(secondHandWidth.toFloat(), 5f, 5f, value)
            invalidate()
        }
    var secondHandWidth = NOT_INITIALIZED
        set(value) {
            field = if (value in 1..100) {
                value
            } else {
                min(max(value, 1), 100)
            }
            secondHandPaint.strokeWidth = field.toFloat() / HUNDRED_PERCENT * MAX_STROKE_WIDTH
            invalidate()
        }
     var secondHandLength = NOT_INITIALIZED
        set(value) {
            field = if (value in 1..100) {
                value
            } else {
                min(max(value, 1), 100)
            }
            invalidate()
        }
    //Настройки минутной стрелки
     var minuteHandColor = NOT_INITIALIZED
        set(value) {
            field = value
            minuteHandPaint.color = value
            minuteHandPaint.setShadowLayer(minuteHandWidth.toFloat(), 5f, 5f, value)
            invalidate()
        }
     var minuteHandWidth = NOT_INITIALIZED
        set(value) {
            field = if (value in 1..100) {
                value
            } else {
                min(max(value, 1), 100)
            }
            minuteHandPaint.strokeWidth = field.toFloat() / HUNDRED_PERCENT * MAX_STROKE_WIDTH
            invalidate()
        }
     var minuteHandLength = NOT_INITIALIZED
        set(value) {
            field = if (value in 1..100) {
                value
            } else {
                min(max(value, 1), 100)
            }
            invalidate()
        }
    //Настройки стрелки часов
     var hourHandColor = NOT_INITIALIZED
        set(value) {
            field = value
            hourHandPaint.color = value
            hourHandPaint.setShadowLayer(hourHandWidth.toFloat(), 5f, 5f, value)
            invalidate()
        }
     var hourHandWidth = NOT_INITIALIZED
        set(value) {
            field = if (value in 1..100) {
                value
            } else {
                min(max(value, 1), 100)
            }
            hourHandPaint.strokeWidth = field.toFloat() / HUNDRED_PERCENT * MAX_STROKE_WIDTH
            invalidate()
        }
     var hourHandLength = NOT_INITIALIZED
        set(value) {
            field = if (value in 1..100) {
                value
            } else {
                min(max(value, 1), 100)
            }
            invalidate()
        }
    //Настройки делений
     var delimiterColor = NOT_INITIALIZED
        set(value) {
            field = value
            delimiterPaint.color = value
            invalidate()
        }
     var delimiterWidth = NOT_INITIALIZED
        set(value) {
            field = if (value in 1..100) {
                value
            } else {
                min(max(value, 1), 100)
            }
            delimiterPaint.strokeWidth = field.toFloat() / HUNDRED_PERCENT * MAX_STROKE_WIDTH
            invalidate()
        }
    //Настройки циферблата
     var numberColor = NOT_INITIALIZED
        set(value) {
            field = value
            numberPaint.color = value
            invalidate()
        }
     var numberWidth = NOT_INITIALIZED
        set(value) {
            field = if (value in 1..100) {
                value
            } else {
                min(max(value, 1), 100)
            }
            numberPaint.strokeWidth = field.toFloat() / HUNDRED_PERCENT * MAX_STROKE_WIDTH
            invalidate()
        }
     var numberSize = NOT_INITIALIZED
        set(value) {
            field = if (value in 1..100) {
                value
            } else {
                min(max(value, 1), 100)
            }
            numberPaint.textSize =  field.toFloat() / HUNDRED_PERCENT * MAX_NUMBER_SIZE
            invalidate()
        }
    //Настройки круга часов
     var circleBackgroundColor = NOT_INITIALIZED
        set(value) {
            field = value
            backGroundCirclePaint.color = value
            invalidate()
        }
     var circleColor = NOT_INITIALIZED
        set(value) {
            field = value
            circlePaint.color = value
            circlePaint.setShadowLayer(circleWidth.toFloat(), 5f, 5f, value)
            invalidate()
        }
     var circleWidth = NOT_INITIALIZED
        set(value) {
            field = if (value in 1..100) {
                value
            } else {
                min(max(value, 1), 100)
            }
            circlePaint.strokeWidth = field.toFloat() / HUNDRED_PERCENT * MAX_STROKE_WIDTH
            invalidate()
        }

    private var center = PointF(NOT_INITIALIZED_FLOAT, NOT_INITIALIZED_FLOAT)
    private var radius = NOT_INITIALIZED_FLOAT
    private var delimiterRadius = NOT_INITIALIZED_FLOAT
    private var numberRadius = NOT_INITIALIZED_FLOAT

    private var timeDelimitersCoordinates = ArrayList<PointF>()
    private var numberCoordinates = ArrayList<PointF>()

    private var secondHandPoints = Pair(
        PointF(NOT_INITIALIZED_FLOAT, NOT_INITIALIZED_FLOAT),
        PointF(NOT_INITIALIZED_FLOAT, NOT_INITIALIZED_FLOAT)
    )
    private var minuteHandPoints = Pair(
        PointF(NOT_INITIALIZED_FLOAT, NOT_INITIALIZED_FLOAT),
        PointF(NOT_INITIALIZED_FLOAT, NOT_INITIALIZED_FLOAT)
    )
    private var hourHandPoints = Pair(
        PointF(NOT_INITIALIZED_FLOAT, NOT_INITIALIZED_FLOAT),
        PointF(NOT_INITIALIZED_FLOAT, NOT_INITIALIZED_FLOAT)
    )

    //Кисти для рисования
    private var secondHandPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var minuteHandPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var hourHandPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var delimiterPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var numberPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var backGroundCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {

        if (attributeSet != null) {
            initAttributes(attributeSet, defStyleAttr, defStyleRes)
        } else {
            initDefaultValues()
        }
        initPaints()

        turnOnClocks()
    }

    private fun initAttributes(attributeSet: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.VKCustomClocksView,
            defStyleAttr,
            defStyleRes
        )
        secondHandColor =
            typedArray.getColor(R.styleable.VKCustomClocksView_secondHandColor, DEFAULT_COLOR)
        secondHandWidth = typedArray.getInt(
            R.styleable.VKCustomClocksView_secondHandWidth,
            DEFAULT_SECOND_HAND_WIDTH
        )
        secondHandLength = typedArray.getInt(
            R.styleable.VKCustomClocksView_secondHandLength,
            DEFAULT_SECOND_HAND_LENGTH
        )

        minuteHandColor =
            typedArray.getColor(R.styleable.VKCustomClocksView_minuteHandColor, DEFAULT_COLOR)
        minuteHandWidth = typedArray.getInt(
            R.styleable.VKCustomClocksView_minuteHandWidth,
            DEFAULT_MINUTE_HAND_WIDTH
        )
        minuteHandLength = typedArray.getInt(
            R.styleable.VKCustomClocksView_minuteHandLength,
            DEFAULT_MINUTE_HAND_LENGTH
        )

        hourHandColor =
            typedArray.getColor(R.styleable.VKCustomClocksView_hourHandColor, DEFAULT_COLOR)
        hourHandWidth =
            typedArray.getInt(R.styleable.VKCustomClocksView_hourHandWidth, DEFAULT_HOUR_HAND_WIDTH)
        hourHandLength = typedArray.getInt(
            R.styleable.VKCustomClocksView_hourHandLength,
            DEFAULT_HOUR_HAND_LENGTH
        )

        delimiterColor =
            typedArray.getColor(R.styleable.VKCustomClocksView_delimiterColor, DEFAULT_COLOR)
        delimiterWidth =
            typedArray.getInt(R.styleable.VKCustomClocksView_delimiterSize, DEFAULT_DELIMITER_SIZE)

        numberColor = typedArray.getColor(R.styleable.VKCustomClocksView_numberColor, DEFAULT_COLOR)
        numberWidth =
            typedArray.getInt(R.styleable.VKCustomClocksView_numberWidth, DEFAULT_NUMBER_SIZE)
        numberSize =
            typedArray.getInt(R.styleable.VKCustomClocksView_numberSize, 80)

        circleBackgroundColor =
            typedArray.getColor(R.styleable.VKCustomClocksView_circleBackgroundColor, Color.WHITE)
        circleColor = typedArray.getColor(R.styleable.VKCustomClocksView_circleColor, DEFAULT_COLOR)
        circleWidth =
            typedArray.getInt(R.styleable.VKCustomClocksView_circleSize, DEFAULT_CIRCLE_SIZE)

        typedArray.recycle()
    }

    private fun initDefaultValues() {
        secondHandColor = DEFAULT_COLOR
        secondHandWidth = DEFAULT_SECOND_HAND_WIDTH
        secondHandLength = DEFAULT_SECOND_HAND_LENGTH

        minuteHandColor = DEFAULT_COLOR
        minuteHandWidth = DEFAULT_MINUTE_HAND_WIDTH
        minuteHandLength = DEFAULT_MINUTE_HAND_LENGTH

        hourHandColor = DEFAULT_COLOR
        hourHandWidth = DEFAULT_HOUR_HAND_WIDTH
        hourHandLength = DEFAULT_HOUR_HAND_LENGTH

        delimiterColor = DEFAULT_COLOR
        delimiterWidth = DEFAULT_DELIMITER_SIZE

        numberColor = DEFAULT_COLOR
        numberWidth = DEFAULT_NUMBER_SIZE

        circleBackgroundColor = Color.WHITE
        circleColor = DEFAULT_COLOR
        circleWidth = DEFAULT_CIRCLE_SIZE
    }

    private fun initPaints() {
        secondHandPaint.apply {
            color = secondHandColor
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeWidth = secondHandWidth / HUNDRED_PERCENT * MAX_STROKE_WIDTH
            setShadowLayer(secondHandWidth.toFloat(), 5f, 5f, secondHandColor)
        }

        minuteHandPaint.apply {
            color = minuteHandColor
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeWidth = minuteHandWidth / HUNDRED_PERCENT * MAX_STROKE_WIDTH
            setShadowLayer(minuteHandWidth.toFloat(), 5f, 5f, minuteHandColor)
        }

        hourHandPaint.apply {
            color = hourHandColor
            style = Paint.Style.STROKE
            strokeWidth = hourHandWidth / HUNDRED_PERCENT * MAX_STROKE_WIDTH
            setShadowLayer(hourHandWidth.toFloat(), 5f, 5f, hourHandColor)
            strokeCap = Paint.Cap.ROUND
        }

        circlePaint.apply {
            color = circleColor
            style = Paint.Style.STROKE
            strokeWidth = circleWidth.toFloat()
            setShadowLayer(circleWidth.toFloat(), 10f, 10f, circleColor)
        }

        backGroundCirclePaint.apply {
            color = circleBackgroundColor
            style = Paint.Style.FILL
        }

         delimiterPaint.apply {
            color = delimiterColor
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeWidth = delimiterWidth.toFloat()
        }

        numberPaint.apply {
            color = numberColor
            textSize = numberSize / HUNDRED_PERCENT * MAX_NUMBER_SIZE
            style = Paint.Style.STROKE
            strokeWidth = numberWidth.toFloat()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(canvas == null){
            throw Exception("Canvas in sull")
        }
        drawMainCircle(canvas)
        drawDelimiters(canvas)
        drawNumber(canvas)
        drawHand(canvas, secondHandPoints, secondHandPaint)
        drawHand(canvas, minuteHandPoints, minuteHandPaint)
        drawHand(canvas, hourHandPoints, hourHandPaint)
    }

    private fun drawMainCircle(canvas: Canvas){
        canvas.drawCircle(center.x, center.y, radius, backGroundCirclePaint)
        canvas.drawCircle(center.x, center.y, radius, circlePaint)
    }

    private fun drawDelimiters(canvas: Canvas) {
        for (index in timeDelimitersCoordinates.indices) {
            if (index % 5 == 0) {
                delimiterPaint.color = delimiterColor
            } else {
                delimiterPaint.color = Color.GRAY
            }
            canvas.drawPoint(
                timeDelimitersCoordinates[index].x,
                timeDelimitersCoordinates[index].y,
                delimiterPaint
            )
        }
    }

    private fun drawNumber(canvas: Canvas) {
        var hours = 12
        var numberRect = Rect()
        for (point in numberCoordinates) {
            numberPaint.getTextBounds(hours.toString(), 0, hours.toString().length, numberRect)
            canvas.drawText(
                hours.toString(),
                point.x - (numberRect.width() / 2),
                point.y + (numberRect.height() / 2),
                numberPaint
            )
            if (hours == 12) {
                hours = 1
            } else {
                hours++
            }
        }
    }

    private fun drawHand(canvas: Canvas, points: Pair<PointF, PointF>, paint: Paint) {
        canvas.drawLine(
            points.first.x,
            points.first.y,
            points.second.x,
            points.second.y,
            paint
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val safeWidth = w - paddingLeft - paddingRight
        val safeHeight = h - paddingTop - paddingBottom
        val safeRadius = Math.min(safeHeight, safeWidth) / 2f

        radius = safeRadius * PADDING_FROM_MAX_RADIUS
        center.x = paddingLeft + safeWidth / 2f
        center.y = paddingTop + safeHeight / 2f

        delimiterRadius = computeRadiusForExtraCircle(radius, circleWidth)
        numberRadius = computeRadiusForExtraCircle(delimiterRadius, 0, 0.8f)

        resetValues()

        timeDelimitersCoordinates = computeExtraCirclePointsByRadius(60, delimiterRadius)
        numberCoordinates = computeExtraCirclePointsByRadius(12, numberRadius)
    }

    private fun resetValues(){
        MAX_STROKE_WIDTH = (radius / 10).toInt()
        MAX_NUMBER_SIZE = radius / 3

        secondHandWidth = secondHandWidth
        minuteHandWidth = minuteHandWidth
        hourHandWidth = hourHandWidth
        numberWidth = numberWidth
        numberSize = numberSize
        delimiterWidth = delimiterWidth

    }

    private fun computeExtraCirclePointsByRadius(
        countOfIndicator: Int,
        byRadius: Float
    ): ArrayList<PointF> {
        val angle = 360f / countOfIndicator
        val result = ArrayList<PointF>()
        for (indicator in 0 until countOfIndicator) {
            val point = PointF()
            val cosAngle = cos(Math.toRadians(indicator * angle - STANDARD_ANGEL_TO_ZERO)).toFloat()
            val sinAngle = sin(Math.toRadians(indicator * angle - STANDARD_ANGEL_TO_ZERO)).toFloat()
            point.x = center.x + byRadius * cosAngle
            point.y = center.y + byRadius * sinAngle
            result.add(point)
        }
        return result
    }

    private fun computeRadiusForExtraCircle(
        byRadius: Float,
        sizeOfCircle: Int = 0,
        padding: Float = PADDING_FROM_MAX_RADIUS
    ): Float {
        return (byRadius - sizeOfCircle / 2) * padding
    }

    private fun turnOnClocks() {
        thread {
            while (true) {
                Thread.sleep(1000)
                Handler(Looper.getMainLooper()).post {
                    updateTimer()
                }
            }
        }
    }

    private fun updateTimer() {
        seconds = timer.seconds
        minutes = timer.minutes
        hours = timer.hours
        findPositionsToDraw()

        invalidate()
    }

    private fun findPositionsToDraw() {
        findHandPoints(seconds, DEGREES_PER_SECOND, secondHandLength, secondHandPoints)
        findHandPoints(minutes, DEGREES_PER_MINUTE, minuteHandLength, minuteHandPoints)
        findHandPoints(hours, DEGREES_PER_HOUR, hourHandLength, hourHandPoints)
    }

    private fun findHandPoints(
        time: Int,
        degrees: Float,
        length: Int,
        points: Pair<PointF, PointF>
    ) {
        val startCosAngle = findCos(time * degrees + 180)
        val startSinAngle = findSin(time * degrees + 180)
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

    companion object {
        private var MAX_STROKE_WIDTH = 50
        private var MAX_NUMBER_SIZE = 50f

        private const val NOT_INITIALIZED = -1
        private const val NOT_INITIALIZED_FLOAT = 0f

        private const val DEFAULT_COLOR = Color.BLACK

        private const val DEFAULT_SECOND_HAND_WIDTH = 10
        private const val DEFAULT_SECOND_HAND_LENGTH = 80

        private const val DEFAULT_MINUTE_HAND_WIDTH = 20
        private const val DEFAULT_MINUTE_HAND_LENGTH = 40

        private const val DEFAULT_HOUR_HAND_WIDTH = 30
        private const val DEFAULT_HOUR_HAND_LENGTH = 20

        private const val DEFAULT_DELIMITER_SIZE = 5
        private const val DEFAULT_NUMBER_SIZE = 5
        private const val DEFAULT_CIRCLE_SIZE = 10

        private const val DEGREES_PER_SECOND = 6f
        private const val DEGREES_PER_MINUTE = 6f
        private const val DEGREES_PER_HOUR = 30f

        private const val STANDARD_ANGEL_TO_ZERO = 90.0

        private const val PADDING_FROM_MAX_RADIUS = 0.9f

        private const val HUNDRED_PERCENT = 100f
    }

}
