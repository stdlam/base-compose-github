package com.practices.githubusers.base

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import com.practices.githubusers.graph.Destination
import com.practices.githubusers.internal.core.navigation.compositionLocal.LocalNavigator
import com.practices.githubusers.internal.core.util.EmptyCompose
import com.practices.githubusers.internal.core.util.Param1Compose
import com.practices.githubusers.internal.core.util.clearFocusOnTapOutSide
import com.practices.githubusers.internal.core.util.clickAnimation
import com.practices.githubusers.internal.core.widget.LoadingSpinner
import kotlinx.coroutines.flow.collectLatest
import com.practices.githubusers.internal.core.platform.BaseViewModel
import com.practices.githubusers.ui.component.dialog.NoticeDialog

val isPreviewMode: Boolean
    @Composable
    get() = LocalInspectionMode.current

@Composable
fun BaseScreen(
    viewModel: BaseViewModel,
    backgroundColor: Color = Color.White,
    content: EmptyCompose,
) {
    val updateContent by rememberUpdatedState(newValue = content)
    val showDialog by viewModel.failure.collectAsState(null)
    if (!isPreviewMode) {
        val navigator = LocalNavigator.current
        BackHandler(onBack = navigator::goBack)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .clearFocusOnTapOutSide(),
    ) {
        updateContent()
        LoadingSpinner(loadingState = viewModel.loading)
        NoticeDialog(state = showDialog) {
            viewModel.dismissFailure()
        }
    }
}