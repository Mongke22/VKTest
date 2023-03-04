package com.example.vktest.customClocksGroup

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.vktest.CurrentTimePicker
import com.example.vktest.R
import com.example.vktest.databinding.VkCustomClocksViewGroupBinding
import kotlin.concurrent.thread

class VKCustomClocksViewGroup(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        R.style.DefaultVKClocksGroupStyle
    )

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.vkCustomClocksGroupStyle
    )

    constructor(context: Context) : this(context, null)

    private val timePicker = CurrentTimePicker()
    private val binding: VkCustomClocksViewGroupBinding

    var secondHandColor = NOT_INITIALIZED
        set(value) {
            field = value
            binding.secondsHand.handColor = value
        }
    var secondHandWidth = NOT_INITIALIZED
        set(value) {
            field = value
            binding.secondsHand.handWidth = value
        }
    var secondHandLength = NOT_INITIALIZED
        set(value) {
            field = value
            binding.secondsHand.handLength = value
        }

    //Настройки минутной стрелки
    var minuteHandColor = NOT_INITIALIZED
        set(value) {
            field = value
            binding.minutesHand.handColor = value
        }
    var minuteHandWidth = NOT_INITIALIZED
        set(value) {
            field = value
            binding.minutesHand.handWidth = value
        }
    var minuteHandLength = NOT_INITIALIZED
        set(value) {
            field = value
            binding.minutesHand.handLength = value
        }

    //Настройки стрелки часов
    var hourHandColor = NOT_INITIALIZED
        set(value) {
            field = value
            binding.hoursHand.handColor = value
        }
    var hourHandWidth = NOT_INITIALIZED
        set(value) {
            field = value
            binding.hoursHand.handWidth = value
        }
    var hourHandLength = NOT_INITIALIZED
        set(value) {
            field = value
            binding.hoursHand.handLength = value
        }

    //Настройки делений
    var delimiterColor = NOT_INITIALIZED
        set(value) {
            field = value
            binding.pointDelimiters.delimiterColor = value
        }
    var delimiterWidth = NOT_INITIALIZED
        set(value) {
            field = value
            binding.pointDelimiters.delimiterWidth = value
        }

    //Настройки циферблата
    var numberColor = NOT_INITIALIZED
        set(value) {
            field = value
            binding.numberDelimiters.delimiterColor = value
        }
    var numberWidth = NOT_INITIALIZED
        set(value) {
            field = value
            binding.numberDelimiters.delimiterWidth = value
        }
    var numberSize = NOT_INITIALIZED
        set(value) {
            field = value
            binding.numberDelimiters.delimiterSize = value
        }

    //Настройки круга часов
    var circleBackgroundColor = NOT_INITIALIZED
        set(value) {
            field = value
            binding.mainCircle.backgroundCircleColor = value
        }
    var circleColor = NOT_INITIALIZED
        set(value) {
            field = value
            binding.mainCircle.circleColor = value
        }
    var circleWidth = NOT_INITIALIZED
        set(value) {
            field = value
            binding.mainCircle.circleWidth = value
        }

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.vk_custom_clocks_view_group, this, true)
        binding = VkCustomClocksViewGroupBinding.bind(this)

        if (attrs != null) {
            initAttributes(attrs, defStyleAttr, defStyleRes)
        } else {
            initDefaultValues()
        }

        runClocks()
    }

    private fun initAttributes(attributeSet: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.VKCustomClocksViewGroup,
            defStyleAttr,
            defStyleRes
        )
        secondHandColor =
            typedArray.getColor(
                R.styleable.VKCustomClocksViewGroup_secondsHandColor,
                DEFAULT_COLOR
            )
        secondHandWidth = typedArray.getInt(
            R.styleable.VKCustomClocksViewGroup_secondsHandWidth,
            DEFAULT_SECOND_HAND_WIDTH
        )
        secondHandLength = typedArray.getInt(
            R.styleable.VKCustomClocksViewGroup_secondsHandLength,
            DEFAULT_SECOND_HAND_LENGTH
        )

        minuteHandColor =
            typedArray.getColor(
                R.styleable.VKCustomClocksViewGroup_minutesHandColor,
                DEFAULT_COLOR
            )
        minuteHandWidth = typedArray.getInt(
            R.styleable.VKCustomClocksViewGroup_minutesHandWidth,
            DEFAULT_MINUTE_HAND_WIDTH
        )
        minuteHandLength = typedArray.getInt(
            R.styleable.VKCustomClocksViewGroup_minutesHandLength,
            DEFAULT_MINUTE_HAND_LENGTH
        )

        hourHandColor =
            typedArray.getColor(
                R.styleable.VKCustomClocksViewGroup_hoursHandColor,
                DEFAULT_COLOR
            )
        hourHandWidth =
            typedArray.getInt(
                R.styleable.VKCustomClocksViewGroup_hoursHandWidth,
                DEFAULT_HOUR_HAND_WIDTH
            )
        hourHandLength = typedArray.getInt(
            R.styleable.VKCustomClocksViewGroup_hoursHandLength,
            DEFAULT_HOUR_HAND_LENGTH
        )

        delimiterColor =
            typedArray.getColor(
                R.styleable.VKCustomClocksViewGroup_delimitersColor,
                DEFAULT_COLOR
            )
        delimiterWidth =
            typedArray.getInt(
                R.styleable.VKCustomClocksViewGroup_delimitersSize,
                DEFAULT_DELIMITER_SIZE
            )

        numberColor = typedArray.getColor(
            R.styleable.VKCustomClocksViewGroup_numbersColor,
            DEFAULT_COLOR
        )
        numberWidth =
            typedArray.getInt(
                R.styleable.VKCustomClocksViewGroup_numbersWidth,
                DEFAULT_NUMBER_WIDTH
            )
        numberSize =
            typedArray.getInt(
                R.styleable.VKCustomClocksViewGroup_numbersSize,
                DEFAULT_NUMBER_SIZE
            )

        circleBackgroundColor =
            typedArray.getColor(
                R.styleable.VKCustomClocksViewGroup_circlesBackgroundColor,
                Color.WHITE
            )
        circleColor = typedArray.getColor(
            R.styleable.VKCustomClocksViewGroup_circlesColor,
            DEFAULT_COLOR
        )
        circleWidth =
            typedArray.getInt(
                R.styleable.VKCustomClocksViewGroup_circlesSize,
                DEFAULT_CIRCLE_SIZE
            )

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

    private fun runClocks() {
        thread {
            while (true) {
                Thread.sleep(1000)
                Handler(Looper.getMainLooper()).post {
                    binding.secondsHand.currentTime = timePicker.seconds
                    binding.minutesHand.currentTime = timePicker.minutes
                    binding.hoursHand.currentTime = timePicker.hours
                }
            }
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()!!
        val savedState = VKCustomClocksViewGroup.SavedState(superState)
        savedState.secondHandColor = secondHandColor
        savedState.secondHandWidth = secondHandWidth
        savedState.secondHandLength = secondHandLength
        savedState.minuteHandColor = minuteHandColor
        savedState.minuteHandWidth = minuteHandWidth
        savedState.minuteHandLength = minuteHandLength
        savedState.hourHandColor = hourHandColor
        savedState.hourHandWidth = hourHandWidth
        savedState.hourHandLength = hourHandLength
        savedState.delimiterColor = delimiterColor
        savedState.delimiterWidth = delimiterWidth
        savedState.numberColor = numberColor
        savedState.numberWidth = numberWidth
        savedState.numberSize = numberSize
        savedState.circleBackgroundColor = circleBackgroundColor
        savedState.circleColor = circleColor
        savedState.circleWidth = circleWidth
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val savedState = state as VKCustomClocksViewGroup.SavedState
        super.onRestoreInstanceState(savedState.superState)
        secondHandColor = savedState.secondHandColor
        secondHandWidth = savedState.secondHandWidth
        secondHandLength = savedState.secondHandLength
        minuteHandColor = savedState.minuteHandColor
        minuteHandWidth = savedState.minuteHandWidth
        minuteHandLength = savedState.minuteHandLength
        hourHandColor = savedState.hourHandColor
        hourHandWidth = savedState.hourHandWidth
        hourHandLength = savedState.hourHandLength
        delimiterColor = savedState.delimiterColor
        delimiterWidth = savedState.delimiterWidth
        numberColor = savedState.numberColor
        numberWidth = savedState.numberWidth
        numberSize = savedState.numberSize
        circleBackgroundColor = savedState.circleBackgroundColor
        circleColor = savedState.circleColor
        circleWidth = savedState.circleWidth
    }
    class SavedState : BaseSavedState {
        var secondHandColor: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var secondHandWidth: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var secondHandLength: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var minuteHandColor: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var minuteHandWidth: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var minuteHandLength: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var hourHandColor: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var hourHandWidth: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var hourHandLength: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var delimiterColor: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var delimiterWidth: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var numberColor: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var numberWidth: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var numberSize: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var circleBackgroundColor: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var circleColor: Int = VKCustomClocksViewGroup.NOT_INITIALIZED
        var circleWidth: Int = VKCustomClocksViewGroup.NOT_INITIALIZED

        constructor(superState: Parcelable) : super(superState)
        constructor(parcel: Parcel) : super(parcel) {
            secondHandColor = parcel.readInt()
            secondHandWidth = parcel.readInt()
            secondHandLength = parcel.readInt()
            minuteHandColor = parcel.readInt()
            minuteHandWidth = parcel.readInt()
            minuteHandLength = parcel.readInt()
            hourHandColor = parcel.readInt()
            hourHandWidth = parcel.readInt()
            hourHandLength = parcel.readInt()
            delimiterColor = parcel.readInt()
            delimiterWidth = parcel.readInt()
            numberColor = parcel.readInt()
            numberWidth = parcel.readInt()
            numberSize = parcel.readInt()
            circleBackgroundColor = parcel.readInt()
            circleColor = parcel.readInt()
            circleWidth = parcel.readInt()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.apply {
                writeInt(secondHandColor)
                writeInt(secondHandWidth)
                writeInt(secondHandLength)
                writeInt(minuteHandColor)
                writeInt(minuteHandWidth)
                writeInt(minuteHandLength)
                writeInt(hourHandColor)
                writeInt(hourHandWidth)
                writeInt(hourHandLength)
                writeInt(delimiterColor)
                writeInt(delimiterWidth)
                writeInt(numberColor)
                writeInt(numberWidth)
                writeInt(numberSize)
                writeInt(circleBackgroundColor)
                writeInt(circleColor)
                writeInt(circleWidth)
            }

        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(source: Parcel): SavedState {
                    return SavedState(source)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return Array(size) { null }
                }

            }
        }

    }
    companion object {

        const val DEFAULT_COLOR = Color.BLACK

        const val DEFAULT_SECOND_HAND_WIDTH = 10
        const val DEFAULT_SECOND_HAND_LENGTH = 80

        const val DEFAULT_MINUTE_HAND_WIDTH = 20
        const val DEFAULT_MINUTE_HAND_LENGTH = 40

        const val DEFAULT_HOUR_HAND_WIDTH = 30
        const val DEFAULT_HOUR_HAND_LENGTH = 20

        const val DEFAULT_DELIMITER_SIZE = 5
        const val DEFAULT_NUMBER_WIDTH = 5
        const val DEFAULT_NUMBER_SIZE = 80
        const val DEFAULT_CIRCLE_SIZE = 10

        const val NOT_INITIALIZED = 0
        const val NOT_INITIALIZED_FLOAT = 0f
    }
}