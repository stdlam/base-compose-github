package com.practices.githubusers.ui.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import com.practices.githubusers.R

@Composable
fun FollowInfoComponent(number: Int, max: Int, info: String, iconId: Int) {
    Column (
        modifier = Modifier
            .wrapContentSize()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(40.dp)
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
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.Center)
            )
        }

        Text(
            text = if (number > max) "$max+" else "$number",
            fontWeight = FontWeight.W500,
            fontSize = 12.sp,
            color = Color(
                ResourcesCompat.getColor(
                    LocalContext.current.resources,
                    R.color.black,
                    null
                )
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = info,
            fontWeight = FontWeight.W400,
            fontSize = 12.sp,
            color = Color(
                ResourcesCompat.getColor(
                    LocalContext.current.resources,
                    R.color.neutral_500,
                    null
                )
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

    }
}