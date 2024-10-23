package com.practices.githubusers.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.practices.githubusers.base.BaseScreen
import com.practices.githubusers.internal.core.util.EmptyFunc
import com.practices.githubusers.internal.core.util.ParamFunc2
import com.practices.githubusers.internal.domain.user.model.UserItem
import com.practices.githubusers.ui.component.dialog.NoticeDialog
import com.practices.githubusers.ui.component.space.Space

@Composable
fun HomeScreenWrapper(
    onUserClick: ParamFunc2<UserItem, Int>? = null,
    homeViewModel: HomeViewModel = hiltViewModel<HomeViewModel>(),
) {
    val state by homeViewModel.state.collectAsState()
    BaseScreen(
        viewModel = homeViewModel
    ) {
        HomeScreen(
            state,
            onLoadMore = {
                homeViewModel.fetchRemoteData()
            },
            onUserClick = onUserClick,
        )
    }
}

@Preview(device = "id:pixel_4")
@Composable
fun HomeScreen(
    state: HomeViewModel.HomeScreenDataWrapper? = null,
    onLoadMore: EmptyFunc? = null,
    onUserClick: ParamFunc2<UserItem, Int>? = null
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Github Users",
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                color = Color(0xFF141316),
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Space(space = 20)
                HomeListUser(
                    state = state,
                    onLoadMore = onLoadMore,
                    onUserClick = onUserClick,
                    isLoading = state?.isLoading == true
                )
            }
        }
    }
}