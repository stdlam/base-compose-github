package com.practices.githubusers.internal.core.platform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import com.practices.githubusers.internal.domain.base.UseCase
import com.practices.githubusers.internal.domain.base.exception.Failure
import com.practices.githubusers.internal.domain.base.functional.Resource
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class BaseViewModel(
    private vararg val useCases: UseCase<*, *>?
) : ViewModel() {

    private val _failure: Channel<Failure?> = Channel(Channel.BUFFERED)
    val failure: Flow<Failure?> = _failure.receiveAsFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    protected inline fun launch(
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
        crossinline job: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(coroutineContext) {
        job.invoke(this)
    }

    override fun onCleared() {
        useCases.let { userCases ->
            if (userCases.isNotEmpty()) userCases.forEach { it?.onCleared() }
        }
        super.onCleared()
    }

    protected suspend fun handleFailure(failure: Failure) {
        _loading.emit(false)
        _failure.send(failure)
    }

    fun dismissFailure() = launch {
        _failure.send(null)
    }

    protected suspend fun showLoading() {
        _loading.emit(true)
    }

    protected suspend fun hideLoading() {
        _loading.emit(false)
    }

    protected fun <Type> Flow<Resource<Type>>.transformUI(catchFailure: Boolean = true) =
        this.onEach { resource ->
            when (resource) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> hideLoading()
                is Resource.Error -> {
                    if (catchFailure) handleFailure(resource.failure) else hideLoading()
                }
            }
        }
}