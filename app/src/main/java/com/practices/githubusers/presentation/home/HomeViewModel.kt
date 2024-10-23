package com.practices.githubusers.presentation.home

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.practices.githubusers.internal.core.platform.BaseViewModel
import com.practices.githubusers.internal.domain.base.functional.Resource
import com.practices.githubusers.internal.domain.user.model.UserItem
import com.practices.githubusers.internal.domain.user.repository.UserRepository
import com.practices.githubusers.internal.domain.user.usercase.GetGithubUsersUseCase
import timber.log.Timber
import javax.inject.Inject

/**
 * A ViewModel responsible for fetching data from the server and exposing it to the UI.
 *
 * This ViewModel uses a [GetGithubUsersUseCase] to fetch a list of users from the [UserRepository].
 * The fetched data is then exposed as a [StateFlow] of [HomeScreenDataWrapper] for the UI to observe.
 *
 * The ViewModel automatically initiates the data fetching process when it's created.
 * It handles any errors that occur during the fetching process and updates the UI accordingly.
 *
 * A data wrapper [HomeScreenDataWrapper] stores a list if [UserItem] and loading state
 *
 * @property _state A [MutableStateFlow] that emits UI states that fetched from the server.
 * @property state A [StateFlow] that exposes to UI any updates.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val githubUsersUseCase: GetGithubUsersUseCase
) : BaseViewModel() {

    data class HomeScreenDataWrapper(
        val users: ArrayList<UserItem>,
        var isLoading: Boolean = false
    )

    private val dataWrapper = HomeScreenDataWrapper(
        arrayListOf()
    )

    private val _state = MutableStateFlow<HomeScreenDataWrapper?>(null)
    val state = _state.asStateFlow()

    init {
        fetchRemoteData()
    }

    fun fetchRemoteData() = launch {
        val sinceId = if (dataWrapper.users.isEmpty()) 0 else dataWrapper.users.last().id
        githubUsersUseCase.execute(GetGithubUsersUseCase.Params(sinceId)).collect {
            when (it) {
                is Resource.Error -> {
                    Timber.e("loadData - error=${it.failure.message}")
                    _state.emit(dataWrapper.copy(isLoading = false))
                    handleFailure(it.failure)
                }
                is Resource.Success -> {
                    Timber.d("loadData - data=${it.data}")
                    dataWrapper.users.addAll(it.data)
                    dataWrapper.isLoading = false
                    _state.emit(dataWrapper.copy())
                }

                else -> {
                    _state.emit(dataWrapper.copy(isLoading = true))
                }
            }
        }
    }
}