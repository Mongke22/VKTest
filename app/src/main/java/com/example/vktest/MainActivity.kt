package com.example.vktest

import android.content.res.Resources.getSystem
import android.graphics.Color
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
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

        binding.vkTestClocks.setOnClickListener {
            val clocks = it as VKCustomClocksView
            if(clocks.minuteHandColor != Color.RED)
                clocks.minuteHandColor = Color.RED
            else clocks.minuteHandColor = Color.GREEN
        }

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
            else -> {
                binding.hourColorChanger.setBackgroundColor(color)
                binding.vkTestClocks.hourHandColor = color
            }
        }
    }

    override fun onDialogDismissed(dialogId: Int) {
    }

    companion object{
        const val SECOND_COLOR_DIALOG_ID = 0
        const val MINUTE_COLOR_DIALOG_ID = 1
        const val HOUR_COLOR_DIALOG_ID = 2
    }
}