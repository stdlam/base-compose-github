package com.practices.githubusers.internal.core.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.CheckedTextView
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.CheckResult
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.enabled() {
    isEnabled = true
}

fun View.disabled() {
    isEnabled = false
}

fun View.enabled(enabled: Boolean) {
    isEnabled = enabled
}

fun View.visible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun View.viewable(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.textVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun CheckedTextView.checked() {
    isChecked = true
}

fun CheckedTextView.unchecked() {
    isChecked = false
}

fun View.margin(
    left: Float? = null,
    top: Float? = null,
    right: Float? = null,
    bottom: Float? = null
) {
    layoutParams = (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
        left?.run { leftMargin = dpToPx(this) }
        top?.run { topMargin = dpToPx(this) }
        right?.run { rightMargin = dpToPx(this) }
        bottom?.run { bottomMargin = dpToPx(this) }
    }
}

fun View.marginPx(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) {
    layoutParams = (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
        left?.run { leftMargin = this }
        top?.run { topMargin = this }
        right?.run { rightMargin = this }
        bottom?.run { bottomMargin = this }
    }
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

fun View.hideKeyboard(activity: Activity?) {
    hideKeyboard()
    activity?.currentFocus?.let {
        if (it is EditText) it.clearFocus()
    }
}

fun EditText.requestShowKeyboard() {
    requestFocus()
    setSelection(length())
    showKeyboard()
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

@SuppressLint("ClickableViewAccessibility")
fun View.setupHideSoftKeyboard(activity: Activity?) {
    // Set up touch listener for non-text box views to hide keyboard.
    if (this !is EditText) {
        this.setOnTouchListener { _/*v*/, event ->
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> hideKeyboard(activity)
            }
            false
        }
    }
    //If a layout container, iterate over children and seed recursion.
    if (this is ViewGroup) {
        for (i in 0 until this.childCount) {
            this.getChildAt(i).setupHideSoftKeyboard(activity)
        }
    }
}

inline fun View.safeClick(crossinline listener: (v: View) -> Unit) =
    singleClick(500, listener)

inline fun View.singleClick(thresholdMs: Long = 300, crossinline listener: (v: View) -> Unit) {
    var lastClickTime: Long = 0
    setOnClickListener {
        val realTime = SystemClock.elapsedRealtime()
        if (realTime - lastClickTime > thresholdMs) {
            lastClickTime = realTime
            listener(it)
        }
    }
}

fun ImageView.loadImageDrawable(context: Context, drawableId: Int) {
    setImageDrawable(ContextCompat.getDrawable(context, drawableId))
}
@CheckResult
fun RecyclerView.getScrolledUpFlow(): Flow<Boolean> {
    return callbackFlow {
        val listener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                trySend(dy > 0)
            }
        }
        addOnScrollListener(listener)
        awaitClose { removeOnScrollListener(listener) }
    }
}

fun RecyclerView.clearDecorations() {
    if (itemDecorationCount > 0) {
        for (i in itemDecorationCount - 1 downTo 0) {
            removeItemDecorationAt(i)
        }
    }
}