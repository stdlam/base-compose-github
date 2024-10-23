package com.practices.githubusers.base.helpers

import android.content.Context
import androidx.compose.foundation.lazy.LazyListState
import com.practices.githubusers.internal.domain.base.exception.Failure
import com.practices.githubusers.internal.domain.base.exception.TemplateFailure

fun Failure.toErrorString(): String {
    val defError = "Something went wrong! Please try again"
    return when (this) {
        is Failure.SessionExpiredFailure -> "Access limited, try to use other VPN to continue"
        is Failure.NetworkConnection -> "The network connection is lost"
        is Failure.TimeoutConnection -> "Unable to connect to the Internet"
        is Failure.UnknownFailure -> {
            if (Constants.isDevMode) this.unknown?.localizedMessage ?: defError
            else defError
        }
        is Failure.ServerError -> this.error ?: defError
        else -> defError
    }
}

fun TemplateFailure?.toErrorString(context: Context?): CharSequence? {
    return when (this) {
        is TemplateFailure.FailureMessage -> error as? CharSequence
        is TemplateFailure.FailureResources -> (error as? Int)?.let { context?.getString(it) }
        else -> null
    }
}

fun Failure?.toTextFailure(): TemplateFailure.FailureMessage? {
    this ?: return null
    return if (this is Failure.ServerError) TemplateFailure.FailureMessage(this.error)
    else null
}

fun LazyListState.reachedBottom(buffer: Int = 1): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - buffer
}