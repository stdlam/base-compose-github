package com.practices.githubusers.ui.component.item

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.practices.githubusers.R
import com.practices.githubusers.ui.component.space.Space

@Composable
fun ShimmerItem() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        content = {
            val transition = rememberInfiniteTransition(label = "")
            val translateAnim by transition.animateFloat(
                initialValue = 0f,
                targetValue = 1000f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                ),
                label = ""
            )
            val brush = Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(translateAnim, translateAnim),
                end = Offset(translateAnim + 500f, translateAnim + 500f)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(brush)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(
                                    Color(
                                        ResourcesCompat.getColor(
                                            LocalContext.current.resources,
                                            R.color.neutral_50,
                                            null
                                        )
                                    )
                                )
                        )

                        Space(space = 10)

                        Column(modifier = Modifier.weight(1f)) {
                            Box(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(15.dp)
                                    .background(
                                        Color(
                                            ResourcesCompat.getColor(
                                                LocalContext.current.resources,
                                                R.color.neutral_50,
                                                null
                                            )
                                        )
                                    )
                            )
                            Space(space = 5)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(
                                        Color(
                                            ResourcesCompat.getColor(
                                                LocalContext.current.resources,
                                                R.color.neutral_50,
                                                null
                                            )
                                        )
                                    )
                            )
                            Space(space = 5)
                            Box(
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(15.dp)
                                    .background(
                                        Color(
                                            ResourcesCompat.getColor(
                                                LocalContext.current.resources,
                                                R.color.neutral_50,
                                                null
                                            )
                                        )
                                    )
                            )
                        }
                    }
                }
            }
        }
    )
}