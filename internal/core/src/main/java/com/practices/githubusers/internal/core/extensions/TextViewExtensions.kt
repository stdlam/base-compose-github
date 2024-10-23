package com.practices.githubusers.internal.core.extensions

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

fun TextView.text(@StringRes textRes: Int) {
    this.text = (if (textRes != 0) context.getString(textRes) else null)
}

fun TextView.textVisible(@StringRes textRes: Int) {
    this.textVisible(if (textRes != 0) context.getString(textRes) else null)
}

fun TextView.textVisible(text: String?) {
    this.text = text
    this.visibility = if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
}

fun TextView.textVisible(text: CharSequence?) {
    this.text = text
    this.visibility = if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
}

fun TextView.textVisible(text: CharSequence?, type: TextView.BufferType) {
    this.setText(text, type)
    this.visibility = if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
}

fun TextView.textDrawablePadding(source: CharSequence?) {
    this.text = source
    this.compoundDrawablePadding = if (source.isNullOrEmpty()) 0 else context.dpToPx(4f)
}

fun TextView.textArgs(@StringRes resId: Int, vararg formatArgs: Any) {
    this.text = context.getString(resId, *formatArgs)
}

fun TextView.color(@ColorRes color: Int) {
    setTextColor(context.colorCompat(color))
}