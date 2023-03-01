package com.example.vktest

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val clocks = findViewById<VKCustomClocksView>(R.id.vkTestClocks2)
        clocks.setOnClickListener {
            if(clocks.minuteHandColor != Color.RED) {
                clocks.minuteHandColor = Color.RED
            }else{
                clocks.minuteHandColor = Color.BLACK
            }
        }
    }
}