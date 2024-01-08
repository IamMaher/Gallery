package com.example.myapplication.core


fun Long.toDurationString(): String {
    val durationInSeconds = this / 1000
    val hours = durationInSeconds / 3600
    val minutes = (durationInSeconds % 3600) / 60
    val seconds = durationInSeconds % 60

    return if (hours > 0) {
        "%d:%02d:%02d".format(hours, minutes, seconds)
    } else {
        "%02d:%02d".format(minutes, seconds)
    }
}

fun String?.formatDate(): String {
    return when (val value = this?.toLong()) {
        null -> ""
        else -> value.toDurationString()
    }
}