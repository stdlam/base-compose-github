package com.practices.githubusers.presentation.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.practices.githubusers.internal.core.util.EmptyFunc
import com.practices.githubusers.internal.core.util.ParamFunc2
import com.practices.githubusers.internal.core.util.clickAnimation
import com.practices.githubusers.internal.domain.user.model.UserItem
import com.practices.githubusers.ui.component.EndlessLazyColumn
import com.practices.githubusers.ui.component.item.ShimmerItem
import com.practices.githubusers.ui.component.item.UserItemComponent
import timber.log.Timber

@Composable
fun HomeListUser(
    state: HomeViewModel.HomeScreenDataWrapper?,
    onLoadMore: EmptyFunc? = null,
    onUserClick: ParamFunc2<UserItem, Int>? = null,
    isLoading: Boolean = false
) {
    state ?: return

    val existingUsers = state.users

    Timber.d("load EndlessLazyColumn existingUsers=$existingUsers")

    EndlessLazyColumn(
        items = existingUsers,
        itemKey = { user: UserItem -> user.hashCode() },
        itemContent = { user, idx ->
            if (idx == 0) {
                Spacer(modifier = Modifier.height(8.dp))
            }
            UserItemComponent(
                userItem = user,
                modifier = Modifier.clickAnimation(),
                onUserClick = {
                    onUserClick?.invoke(it, idx)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (isLoading && idx == existingUsers.size - 1) {
                ShimmerItem()
            }
        },
        loadingItem = {

        },
        loadMore = {
            onLoadMore?.invoke()
        },
        loading = isLoading,
        modifier = Modifier
            .fillMaxHeight()
    )
}