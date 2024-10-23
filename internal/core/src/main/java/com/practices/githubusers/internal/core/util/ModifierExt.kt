package com.practices.githubusers.internal.core.util

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

fun Modifier.clickAnimation(
    enable: Boolean = true,
    hasAnimation: Boolean = true,
    onClick: EmptyFunc? = null,
): Modifier = composed {
    if (onClick == null) this
    else this.clickable(
        enabled = enable,
        onClick = onClick,
        indication = if (hasAnimation) null else remember {
                    object : Indication {

                    }
        },
        interactionSource = remember {
            MutableInteractionSource()
        }
    )
}

fun Modifier.clearFocusOnTapOutSide() = composed { composed {
    val localFocus = LocalFocusManager.current
    this.pointerInput(Unit) {
        detectTapGestures {
            localFocus.clearFocus()
        }
    }
} }

fun Modifier.headerPadding() = this.padding(top = 40.dp, start = 16.dp, end = 16.dp)


fun Modifier.archiveChildStartPadding() = this.padding(start = 30.dp)