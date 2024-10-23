package com.practices.githubusers.internal.domain.base.extensions

import java.util.*
import kotlin.math.roundToInt

fun <T> List<T>?.orMutableEmpty(): List<T> = this ?: ArrayList()

fun Date?.orEmpty(): Date = this ?: Date()

fun Date.isAtToday(): Boolean {
    val now = Calendar.getInstance()
    val target = Calendar.getInstance()
    target.timeInMillis = this.time
    return now.get(Calendar.YEAR) == target.get(Calendar.YEAR)
            && now.get(Calendar.MONTH) == target.get(Calendar.MONTH)
            && now.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH)
}

fun String.Companion.empty() = ""

inline fun String?.ifNotNullOrEmpty(resolve: (s: String) -> Unit) {
    if (this != null && this.isEmpty().not()) resolve.invoke(this)
}

fun Long.Companion.noId() = -1L

fun Long.Companion.zero() = 0L

fun Long?.orNoId(): Long = this ?: Long.noId()

fun Long.isNoId(): Boolean = this == Long.noId()

fun Long.isNotNoId(): Boolean = this != Long.noId()

fun Long?.isNotNoId(): Boolean = this.orNoId() != Long.noId()

fun Long?.orZero(): Long = this ?: 0L

fun Boolean?.orFalse(): Boolean = this ?: false

fun Int?.orZero(): Int = this ?: 0

fun Int.Companion.zero() = 0

fun Float?.orZero(): Float = this ?: 0.0f

fun Float?.roundUp(): Float {
    this ?: return 0f
    return (this * 100.0f).roundToInt() / 100.0f
}

fun Float.string(): String {
    return if (this.compareTo(this.toInt()) == 0) {
        this.toInt().toString()
    } else toString()
}