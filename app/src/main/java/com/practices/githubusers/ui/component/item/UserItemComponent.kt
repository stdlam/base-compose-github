package com.practices.githubusers.ui.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.practices.githubusers.R
import com.practices.githubusers.internal.core.util.ParamFunc
import com.practices.githubusers.internal.domain.user.model.UserItem
import com.practices.githubusers.ui.component.space.Space

@Composable
fun UserItemComponent(
    userItem: UserItem,
    modifier: Modifier = Modifier,
    onUserClick: ParamFunc<UserItem>? = null,
) {
    val (_, name, avatar, url) = userItem

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        content = {
            Row(
                modifier = Modifier
                    .clickable(
                        onClick = {
                            onUserClick?.invoke(userItem)
                        },
                        indication = ripple(),
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ),
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    val painter = rememberAsyncImagePainter(ImageRequest.Builder(LocalContext.current)
                        .data(data = avatar).apply(
                            block = fun ImageRequest.Builder.() {
                                crossfade(true)
                                placeholder(R.color.grey_400)
                                error(R.color.grey_400)
                            })
                        .build()
                    )

                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                Color(
                                    ResourcesCompat.getColor(
                                        LocalContext.current.resources,
                                        R.color.neutral_50,
                                        null
                                    )
                                )
                            ),
                        contentAlignment = Alignment.Center,
                    ) {

                        Image(
                            painter = painter,
                            contentDescription = "UserAvatar",
                            modifier = Modifier
                                .padding(5.dp)
                                .size(100.dp)
                                .clip(CircleShape)
                        )
                    }

                    Space(space = 10)

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = AnnotatedString(name),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color(
                                LocalContext.current.resources.getColor(R.color.neutral_700, null)
                            ),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
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
                        Text(
                            text = url,
                            fontWeight = FontWeight.W400,
                            fontSize = 12.sp,
                            overflow = TextOverflow.Ellipsis,
                            color = Color(
                                ResourcesCompat.getColor(
                                    LocalContext.current.resources,
                                    R.color.blue_hyperlink,
                                    null
                                )
                            ),
                            textDecoration = TextDecoration.Underline,
                            maxLines = 1,
                        )
                    }
                }
            }
        }
    )
}
