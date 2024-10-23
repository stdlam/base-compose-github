package com.practices.githubusers.ui.component.space

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.Space(space: Int) {
    Space(space = space.dp)
}

@Composable
fun RowScope.Space(space: Dp) {
    Spacer(modifier = Modifier.width(space))
}

@Composable
fun RowScope.Space(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}
