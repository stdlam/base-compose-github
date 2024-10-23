package com.practices.githubusers.presentation.detail

import com.practices.githubusers.internal.core.platform.BaseViewModel
import com.practices.githubusers.internal.domain.base.functional.Resource
import com.practices.githubusers.internal.domain.base.functional.data
import com.practices.githubusers.internal.domain.user.model.UserDetail
import com.practices.githubusers.internal.domain.user.model.UserItem
import com.practices.githubusers.internal.domain.user.repository.UserRepository
import com.practices.githubusers.internal.domain.user.usercase.GetGithubUsersUseCase
import com.practices.githubusers.internal.domain.user.usercase.GetProfileUseCase
import com.practices.githubusers.presentation.home.HomeViewModel.HomeScreenDataWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * A ViewModel responsible for fetching user detail data from the server and exposing it to the UI.
 *
 * This ViewModel uses a [GetProfileUseCase] to fetch a list of users from the [UserRepository].
 * The fetched data is then exposed as a [StateFlow] of [UserDetail] for the UI to observe.
 *
 * It handles any errors that occur during the fetching process and updates the UI accordingly.
 *
 * @property _state A [MutableStateFlow] that emits UI states that fetched from the server.
 * @property state A [StateFlow] that exposes to UI any updates.
 */

@HiltViewModel
class DetailUserViewModel @Inject constructor(
    private val userProfileUseCase: GetProfileUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow(UserDetail.empty())
    val state = _state.asStateFlow()

    fun getProfile(username: String) = launch {
        userProfileUseCase.invoke(username).collect {
            when (it) {
                Resource.Loading -> {
                    showLoading()
                }

                is Resource.Error -> {
                    hideLoading()
                    handleFailure(it.failure)
                }

                else -> {
                    hideLoading()
                    it.data?.let { profile ->
                        _state.emit(profile)
                    }
                }
            }
        }
    }
}