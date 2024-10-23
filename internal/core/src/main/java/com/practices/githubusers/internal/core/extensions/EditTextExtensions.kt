package com.practices.githubusers.internal.core.extensions

import android.text.Editable
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import androidx.annotation.CheckResult
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

inline fun EditText.editorActionClick(vararg actionIds: Int, crossinline action: () -> Unit) {
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionIds.any { it == actionId }) {
            action()
        }
        false
    }
}

inline fun EditText.editorActionConsumed(vararg actionIds: Int, crossinline action: () -> Unit) {
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionIds.any { it == actionId }) {
            action()
            true
        } else {
            false
        }
    }
}

fun EditText?.text(): String {
    return this?.text?.toString() ?: ""
}

fun EditText?.textNotBlank(): String {
    return this?.text?.toString()?.trim() ?: ""
}
@CheckResult
fun EditText.textChangesFlow(): Flow<CharSequence?> {
    return callbackFlow {
        val listener = doOnTextChanged { text, _, _, _ -> trySend(text) }
        awaitClose { removeTextChangedListener(listener) }
    }
}

@CheckResult
fun EditText.afterTextChangesFlow(): Flow<CharSequence?> {
    return callbackFlow {
        val listener = doAfterTextChanged { text -> trySend(text) }
        awaitClose { removeTextChangedListener(listener) }
    }
}

@CheckResult
fun EditText.afterTextChangedFlow(): Flow<Editable> {
    return callbackFlow {
        val listener = doAfterTextChanged { text ->
            if (text != null) trySend(text)
        }
        awaitClose { removeTextChangedListener(listener) }
    }
}

inline fun EditText.safeTextChanged(
    delay: Long = 500L,
    crossinline onTextChange: (CharSequence) -> Unit
) {
    safeTextChanged(delay, doFilterNot = { it == null }, onTextChange = onTextChange)
}

@OptIn(FlowPreview::class)
inline fun EditText.safeTextChanged(
    delay: Long = 500L,
    crossinline doFilterNot: suspend (CharSequence?) -> Boolean,
    crossinline onTextChange: (CharSequence) -> Unit
) {
    textChangesFlow()
        .filterNot { doFilterNot.invoke(it) }
        .debounce(delay)
        .onEach { c ->
            onTextChange.invoke(this.text())
        }.run {
            findViewTreeLifecycleOwner()?.lifecycleScope?.let { lifecycleScope ->
                launchIn(lifecycleScope)
            }
        }
}

fun EditText.togglePassword(enable: Boolean) {
    this.transformationMethod = when (enable) {
        true -> HideReturnsTransformationMethod.getInstance()
        else -> PasswordTransformationMethod.getInstance()
    }
    if (this.isFocused) {
        this.setSelection(this.length())
    }
}