package com.example.mvvmdemo.utils.extensions

import java.util.*

fun Date.getHour(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar[Calendar.HOUR_OF_DAY]
}