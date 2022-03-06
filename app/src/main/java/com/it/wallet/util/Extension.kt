package com.it.wallet.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.SpannableString

inline fun <reified T : Activity> Context.startActivityExt(noinline extra: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.extra()
    startActivity(intent)
}
fun Context.getFormattedTime(
    passedSeconds: Int,
): String {
    val hours = (passedSeconds / 3600) % 24
    val minutes = (passedSeconds / 60) % 60
    val seconds = passedSeconds % 60

    return formatTime(hours, minutes, seconds)
}

fun formatTime(hours: Int, minutes: Int, seconds: Int): String {
    val hoursFormat = "%02d"
    var format = "$hoursFormat:%02d"

    format += ":%02d"
    return String.format(format, hours, minutes, seconds)
}