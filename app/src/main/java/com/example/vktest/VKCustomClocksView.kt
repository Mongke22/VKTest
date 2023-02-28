package com.example.vktest

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.concurrent.thread

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
    private var secondHandColor = NOT_INITIALIZED
        set(value){
            field = value
        }
    private var secondHandWidth = NOT_INITIALIZED
        set(value){
            field = value
        }
    private var secondHandLength = NOT_INITIALIZED
        set(value){
            field = value
        }

    //Настройки минутной стрелки
    private var minuteHandColor = NOT_INITIALIZED
        set(value){
            field = value
        }
    private var minuteHandWidth = NOT_INITIALIZED
        set(value){
            field = value
        }
    private var minuteHandLength = NOT_INITIALIZED
        set(value){
            field = value
        }

    //Настройки стрелки часов
    private var hourHandColor = NOT_INITIALIZED
        set(value){
            field = value
        }
    private var hourHandWidth = NOT_INITIALIZED
        set(value){
            field = value
        }
    private var hourHandLength = NOT_INITIALIZED
        set(value){
            field = value
        }

    //Настройки делений
    private var delimiterColor = NOT_INITIALIZED
        set(value){
            field = value
        }
    private var delimiterSize = NOT_INITIALIZED
        set(value){
            field = value
        }

    //Настройки циферблата
    private var numberColor = NOT_INITIALIZED
        set(value){
            field = value
        }
    private var numberSize = NOT_INITIALIZED
        set(value){
            field = value
        }

    //Настройки круга часов
    private var circleBackgroundColor = NOT_INITIALIZED
        set(value){
            field = value
        }
    private var circleColor = NOT_INITIALIZED
        set(value){
            field = value
        }
    private var circleSize = NOT_INITIALIZED
        set(value){
            field = value
        }

    //Кисти для рисования
    private var secondHandPaint = Paint()
    private var minuteHandPaint = Paint()
    private var hourHandPaint = Paint()
    private var delimiterPaint = Paint()
    private var numberPaint = Paint()
    private var circlePaint = Paint()

    init {

        if (attributeSet != null) {
            initAttributes(attributeSet, defStyleAttr, defStyleRes)
        }else{
            initDefaultValues()
        }

        turnOnClocks()
    }

    private fun initAttributes(attributeSet: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.VKCustomClocksView,
            defStyleAttr,
            defStyleRes
        )
        secondHandColor = typedArray.getColor(R.styleable.VKCustomClocksView_secondHandColor, DEFAULT_COLOR)
        secondHandWidth = typedArray.getInt(R.styleable.VKCustomClocksView_secondHandWidth, DEFAULT_SECOND_HAND_WIDTH)
        secondHandLength = typedArray.getInt(R.styleable.VKCustomClocksView_secondHandLength, DEFAULT_SECOND_HAND_LENGTH)

        minuteHandColor = typedArray.getColor(R.styleable.VKCustomClocksView_minuteHandColor, DEFAULT_COLOR)
        minuteHandWidth = typedArray.getInt(R.styleable.VKCustomClocksView_minuteHandWidth, DEFAULT_MINUTE_HAND_WIDTH)
        minuteHandLength = typedArray.getInt(R.styleable.VKCustomClocksView_minuteHandLength, DEFAULT_MINUTE_HAND_LENGTH)

        hourHandColor = typedArray.getColor(R.styleable.VKCustomClocksView_hourHandColor, DEFAULT_COLOR)
        hourHandWidth = typedArray.getInt(R.styleable.VKCustomClocksView_hourHandWidth, DEFAULT_HOUR_HAND_WIDTH)
        hourHandLength = typedArray.getInt(R.styleable.VKCustomClocksView_hourHandLength, DEFAULT_HOUR_HAND_LENGTH)

        delimiterColor = typedArray.getColor(R.styleable.VKCustomClocksView_delimiterColor, DEFAULT_COLOR)
        delimiterSize = typedArray.getInt(R.styleable.VKCustomClocksView_delimiterSize, DEFAULT_DELIMITER_SIZE)

        numberColor = typedArray.getColor(R.styleable.VKCustomClocksView_numberColor, DEFAULT_COLOR)
        numberSize = typedArray.getInt(R.styleable.VKCustomClocksView_numberSize, DEFAULT_NUMBER_SIZE)

        circleBackgroundColor = typedArray.getColor(R.styleable.VKCustomClocksView_circleBackgroundColor, DEFAULT_COLOR)
        circleColor = typedArray.getColor(R.styleable.VKCustomClocksView_circleColor, DEFAULT_COLOR)
        circleSize = typedArray.getInt(R.styleable.VKCustomClocksView_circleSize, DEFAULT_CIRCLE_SIZE)

        typedArray.recycle()
    }

    private fun initDefaultValues(){
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
        delimiterSize = DEFAULT_DELIMITER_SIZE

        numberColor = DEFAULT_COLOR
        numberSize = DEFAULT_NUMBER_SIZE

        circleBackgroundColor = DEFAULT_COLOR
        circleColor = DEFAULT_COLOR
        circleSize = DEFAULT_CIRCLE_SIZE
    }

    private fun turnOnClocks(){
        thread{
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
        Log.i("Время","$hours:$minutes:$seconds")
        /*findPositionsToDraw()

        invalidate()*/
    }

    private fun findPositionsToDraw(){
       /* findSecondHandFinishPoint()
        findMinuteHandFinishPoint()
        findHourHandFinishPoint()*/
    }

    companion object{

        private const val NOT_INITIALIZED = -1

        private const val DEFAULT_COLOR = Color.BLACK

        private const val DEFAULT_SECOND_HAND_WIDTH = 10
        private const val DEFAULT_SECOND_HAND_LENGTH = 80

        private const val DEFAULT_MINUTE_HAND_WIDTH = 20
        private const val DEFAULT_MINUTE_HAND_LENGTH = 40

        private const val DEFAULT_HOUR_HAND_WIDTH = 30
        private const val DEFAULT_HOUR_HAND_LENGTH = 20

        private const val DEFAULT_DELIMITER_SIZE = 10

        private const val DEFAULT_NUMBER_SIZE = 20

        private const val DEFAULT_CIRCLE_SIZE = 10
    }

}
