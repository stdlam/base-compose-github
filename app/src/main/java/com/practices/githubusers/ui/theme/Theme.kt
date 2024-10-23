package com.practices.githubusers.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun GithubUsersTheme(
    content: @Composable () -> Unit
) {

    val colorScheme = lightColorScheme(
        primary = Color.White,
        onPrimary = Color.Black,
        secondary = Color.LightGray,
        onSecondary = Color.Black,
        background = Color.White,
        onBackground = Color.Black,
        surface = Color.White,
        onSurface = Color.Black
    )
    MaterialTheme(
        colorScheme = colorScheme,
        shapes = MaterialTheme.shapes.copy(
            extraLarge = RoundedCornerShape(50)
        ),
        typography = Typography,
        content = content
    )
}