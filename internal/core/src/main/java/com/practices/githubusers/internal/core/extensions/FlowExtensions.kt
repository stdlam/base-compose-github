package com.practices.githubusers.internal.core.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

inline fun <T> Flow<T>.collectIn(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    crossinline action: suspend (T) -> Unit,
) = owner.lifecycleScope.launch(coroutineContext) {
    owner.lifecycle.repeatOnLifecycle(minActiveState) {
        collect { action(it) }
    }
}

@ExperimentalCoroutinesApi
inline fun <T, K> StateFlow<T>.mapState(
    scope: CoroutineScope,
    crossinline transform: (data: T) -> K
): StateFlow<K> {
    return mapLatest {
        transform(it)
    }.stateIn(scope, SharingStarted.Eagerly, transform(value))
}

@ExperimentalCoroutinesApi
inline fun <T, K> StateFlow<T>.mapState(
    scope: CoroutineScope,
    initialValue: K,
    crossinline transform: suspend (data: T) -> K
): StateFlow<K> {
    return mapLatest {
        transform(it)
    }.stateIn(scope, SharingStarted.Eagerly, initialValue)
}