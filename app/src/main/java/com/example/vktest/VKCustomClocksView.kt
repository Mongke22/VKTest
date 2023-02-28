package com.example.vktest

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

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

    private var delimiterColor = NOT_INITIALIZED
        set(value){
            field = value
        }
    private var delimiterSize = NOT_INITIALIZED
        set(value){
            field = value
        }

    private var numberColor = NOT_INITIALIZED
        set(value){
            field = value
        }
    private var numberSize = NOT_INITIALIZED
        set(value){
            field = value
        }

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

    init {
        if (attributeSet != null) {
            initAttributes(attributeSet, defStyleAttr, defStyleRes)
        }else{
            initDefaultValues()
        }
    }

    private fun initAttributes(attributeSet: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.MyCustomClockVIew,
            defStyleAttr,
            defStyleRes
        )
        secondHandColor = typedArray.getColor(R.styleable.MyCustomClockVIew_secondHandColor, DEFAULT_COLOR)
        secondHandWidth = typedArray.getInt(R.styleable.MyCustomClockVIew_secondHandWidth, DEFAULT_SECOND_HAND_WIDTH)
        secondHandLength = typedArray.getInt(R.styleable.MyCustomClockVIew_secondHandLength, DEFAULT_SECOND_HAND_LENGTH)

        minuteHandColor = typedArray.getColor(R.styleable.MyCustomClockVIew_minuteHandColor, DEFAULT_COLOR)
        minuteHandWidth = typedArray.getInt(R.styleable.MyCustomClockVIew_minuteHandWidth, DEFAULT_MINUTE_HAND_WIDTH)
        minuteHandLength = typedArray.getInt(R.styleable.MyCustomClockVIew_minuteHandLength, DEFAULT_MINUTE_HAND_LENGTH)

        hourHandColor = typedArray.getColor(R.styleable.MyCustomClockVIew_hourHandColor, DEFAULT_COLOR)
        hourHandWidth = typedArray.getInt(R.styleable.MyCustomClockVIew_hourHandWidth, DEFAULT_HOUR_HAND_WIDTH)
        hourHandLength = typedArray.getInt(R.styleable.MyCustomClockVIew_hourHandLength, DEFAULT_HOUR_HAND_LENGTH)

        delimiterColor = typedArray.getColor(R.styleable.MyCustomClockVIew_delimiterColor, DEFAULT_COLOR)
        delimiterSize = typedArray.getInt(R.styleable.MyCustomClockVIew_delimiterSize, DEFAULT_DELIMITER_SIZE)

        numberColor = typedArray.getColor(R.styleable.MyCustomClockVIew_numberColor, DEFAULT_COLOR)
        numberSize = typedArray.getInt(R.styleable.MyCustomClockVIew_numberSize, DEFAULT_NUMBER_SIZE)

        circleBackgroundColor = typedArray.getColor(R.styleable.MyCustomClockVIew_circleBackgroundColor, DEFAULT_COLOR)
        circleColor = typedArray.getColor(R.styleable.MyCustomClockVIew_circleColor, DEFAULT_COLOR)
        circleSize = typedArray.getInt(R.styleable.MyCustomClockVIew_circleSize, DEFAULT_CIRCLE_SIZE)

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
