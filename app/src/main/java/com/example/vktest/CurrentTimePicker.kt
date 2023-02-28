package com.example.vktest

import java.util.*

class CurrentTimePicker {
    private var calendar: Calendar = Calendar.getInstance()
    val seconds: Int
        get() {
            calendar = Calendar.getInstance()
            return calendar.get(Calendar.SECOND)
        }
    val minutes: Int
        get() {
            calendar = Calendar.getInstance()
            return calendar.get(Calendar.MINUTE)
        }
    val hours: Int
        get() {
            calendar = Calendar.getInstance()
            return calendar.get(Calendar.HOUR)
        }

}