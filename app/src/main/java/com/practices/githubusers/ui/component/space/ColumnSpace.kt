package com.practices.githubusers.ui.component.space

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.Space(space: Int) {
    Space(space = space.dp)
}

@Composable
fun ColumnScope.Space(space: Dp) {
    Spacer(modifier = Modifier.height(space))
}

@Composable
fun ColumnScope.Space(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}
