package com.example.vktest

import android.content.res.Resources.getSystem
import android.graphics.Color
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.vktest.customClocksGroup.VKCustomClocksViewGroup
import com.example.vktest.databinding.ActivityMainBinding
import com.jaredrummler.android.colorpicker.ColorPickerDialog
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener


class MainActivity : AppCompatActivity(), ColorPickerDialogListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.secondColorChanger.setBackgroundColor(binding.vkTestClocks.secondHandColor)
        binding.minuteColorChanger.setBackgroundColor(binding.vkTestClocks.minuteHandColor)
        binding.hourColorChanger.setBackgroundColor(binding.vkTestClocks.hourHandColor)

        setUpSeekBarListeners()
        setUpColorChangeListeners()


    }



    override fun onColorSelected(dialogId: Int, color: Int) {
        when(dialogId){
            SECOND_COLOR_DIALOG_ID -> {
                binding.secondColorChanger.setBackgroundColor(color)
                binding.vkTestClocks.secondHandColor = color
            }
            MINUTE_COLOR_DIALOG_ID -> {
                binding.minuteColorChanger.setBackgroundColor(color)
                binding.vkTestClocks.minuteHandColor = color
            }
            HOUR_COLOR_DIALOG_ID -> {
                binding.hourColorChanger.setBackgroundColor(color)
                binding.vkTestClocks.hourHandColor = color
            }

            NUMBER_COLOR_DIALOG_ID -> {
                binding.numberColorChanger.setBackgroundColor(color)
                binding.vkTestClocks.numberColor = color
            }
            POINTS_COLOR_DIALOG_ID -> {
                binding.pointsColorChanger.setBackgroundColor(color)
                binding.vkTestClocks.delimiterColor = color
            }
            CIRCLE_COLOR_DIALOG_ID -> {
                binding.circleColorChanger.setBackgroundColor(color)
                binding.vkTestClocks.circleColor = color
            }
            else -> {
                throw Exception("Wrong color dialog id")
            }
        }
    }

    override fun onDialogDismissed(dialogId: Int) {
    }

    private fun setUpSeekBarListeners(){
        binding.secondHandLengthChanger.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.vkTestClocks.secondHandLength = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.minuteHandLengthChanger.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.vkTestClocks.minuteHandLength = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.hourHandLengthChanger.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.vkTestClocks.hourHandLength = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.secondHandWidthChanger.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.vkTestClocks.secondHandWidth = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.minuteHandWidthChanger.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.vkTestClocks.minuteHandWidth = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.hourHandWidthChanger.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.vkTestClocks.hourHandWidth = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.numberWidthChanger.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.vkTestClocks.numberWidth = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.numberSizeChanger.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.vkTestClocks.numberSize = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.delimiterWidthChanger.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.vkTestClocks.delimiterWidth = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.circleWidthChanger.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.vkTestClocks.circleWidth = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.clocksSizeChanger.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.vkTestClocks.layoutParams = ConstraintLayout.LayoutParams(
                    (300 * getSystem().displayMetrics.density).toInt(),
                    (progress * getSystem().displayMetrics.density).toInt()
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }
    private fun setUpColorChangeListeners() {
        binding.secondColorChanger.setOnClickListener {
            ColorPickerDialog.newBuilder()
                .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                .setAllowPresets(false)
                .setDialogId(SECOND_COLOR_DIALOG_ID)
                .setColor(binding.vkTestClocks.secondHandColor)
                .setShowAlphaSlider(true)
                .show(this);
        }
        binding.minuteColorChanger.setOnClickListener {
            ColorPickerDialog.newBuilder()
                .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                .setAllowPresets(false)
                .setDialogId(MINUTE_COLOR_DIALOG_ID)
                .setColor(binding.vkTestClocks.minuteHandColor)
                .setShowAlphaSlider(true)
                .show(this);
        }
        binding.hourColorChanger.setOnClickListener {
            ColorPickerDialog.newBuilder()
                .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                .setAllowPresets(false)
                .setDialogId(HOUR_COLOR_DIALOG_ID)
                .setColor(binding.vkTestClocks.hourHandColor)
                .setShowAlphaSlider(true)
                .show(this);
        }

        binding.numberColorChanger.setOnClickListener {
            ColorPickerDialog.newBuilder()
                .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                .setAllowPresets(false)
                .setDialogId(NUMBER_COLOR_DIALOG_ID)
                .setColor(binding.vkTestClocks.numberColor)
                .setShowAlphaSlider(true)
                .show(this);
        }
        binding.pointsColorChanger.setOnClickListener {
            ColorPickerDialog.newBuilder()
                .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                .setAllowPresets(false)
                .setDialogId(POINTS_COLOR_DIALOG_ID)
                .setColor(binding.vkTestClocks.delimiterColor)
                .setShowAlphaSlider(true)
                .show(this);
        }
        binding.circleColorChanger.setOnClickListener {
            ColorPickerDialog.newBuilder()
                .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                .setAllowPresets(false)
                .setDialogId(CIRCLE_COLOR_DIALOG_ID)
                .setColor(binding.vkTestClocks.circleColor)
                .setShowAlphaSlider(true)
                .show(this);
        }

        binding.vkTestClocks.setOnClickListener {
            val clocks = it as VKCustomClocksViewGroup
            if(clocks.minuteHandColor != Color.RED)
                clocks.minuteHandColor = Color.RED
            else clocks.minuteHandColor = Color.GREEN
        }
    }
    companion object{
        const val SECOND_COLOR_DIALOG_ID = 0
        const val MINUTE_COLOR_DIALOG_ID = 1
        const val HOUR_COLOR_DIALOG_ID = 2

        const val NUMBER_COLOR_DIALOG_ID = 3
        const val POINTS_COLOR_DIALOG_ID = 4
        const val CIRCLE_COLOR_DIALOG_ID = 5
    }
}