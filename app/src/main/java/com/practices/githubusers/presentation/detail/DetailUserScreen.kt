package com.practices.githubusers.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.practices.githubusers.R
import com.practices.githubusers.base.BaseScreen
import com.practices.githubusers.internal.core.util.EmptyFunc
import com.practices.githubusers.internal.domain.user.model.UserDetail
import com.practices.githubusers.ui.component.item.FollowInfoComponent
import com.practices.githubusers.ui.component.space.Space

@Composable
fun DetailUserScreenWrapper(
    username: String,
    onClick: EmptyFunc? = null,
    onBack: EmptyFunc? = null,
    viewModel: DetailUserViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    DetailUserScreen(viewModel, state, onClick, onBack)
    viewModel.getProfile(username)
}

@Preview
@Composable
fun DetailUserScreen(
    viewModel: DetailUserViewModel,
    state: UserDetail,
    onClick: EmptyFunc? = null,
    onBack: EmptyFunc? = null,
) {
    BaseScreen(
        viewModel = viewModel
    ) {
        // Screen content
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(12.dp)
            ) {
                IconButton(
                    onClick = {
                        onBack?.invoke()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(LocalContext.current.resources.getColor(R.color.neutral_400, null))
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Users Detail",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(
                            LocalContext.current.resources.getColor(R.color.neutral_500, null)
                        )
                    )
                }
            }
            Space(space = 10)
            Card(
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        end = 20.dp
                    ),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                content = {
                    Row {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp)
                        ) {
                            val painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = state.avatar).apply(
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
                                    contentDescription = "",
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .size(100.dp)
                                        .clip(CircleShape)
                                )
                            }

                            Space(space = 20)

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = AnnotatedString(state.userName),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp,
                                    color = Color(
                                        LocalContext.current.resources.getColor(R.color.neutral_500, null)
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
                                Row (
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_location),
                                        contentDescription = "",
                                        colorFilter = ColorFilter.tint(
                                            Color(
                                                ResourcesCompat.getColor(
                                                    LocalContext.current.resources,
                                                    R.color.neutral_500,
                                                    null
                                                )
                                            )
                                        ),
                                        modifier = Modifier
                                            .size(15.dp)
                                    )

                                    Space(space = 2.dp)

                                    Text(
                                        text = state.location,
                                        fontWeight = FontWeight.W400,
                                        fontSize = 14.sp,
                                        overflow = TextOverflow.Ellipsis,
                                        color = Color(
                                            ResourcesCompat.getColor(
                                                LocalContext.current.resources,
                                                R.color.neutral_500,
                                                null
                                            )
                                        ),
                                        maxLines = 1,
                                    )

                                }

                            }
                        }
                    }
                }
            )
            Space(space = 30.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                FollowInfoComponent(
                    number = state.followers,
                    max = 100, "Follower",
                    R.drawable.ic_follow_group
                )
                Space(space = 80.dp)
                FollowInfoComponent(
                    number = state.following,
                    max = 10,
                    "Following",
                    R.drawable.ic_medal
                )

            }

            Space(space = 10.dp)
            Text(
                text = "Blog",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(
                    ResourcesCompat.getColor(
                        LocalContext.current.resources,
                        R.color.black,
                        null
                    )
                ),
                modifier = Modifier
                    .padding(
                        start = 20.dp
                    )
            )

            Space(space = 5.dp)
            Text(
                text = state.blogUrl,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                color = Color(
                    ResourcesCompat.getColor(
                        LocalContext.current.resources,
                        R.color.neutral_400,
                        null
                    )
                ),
                modifier = Modifier
                    .padding(
                        start = 20.dp
                    )
            )
        }
    }
}