package com.practices.githubusers.ui.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.practices.githubusers.base.helpers.toErrorString
import com.practices.githubusers.internal.domain.base.exception.Failure
import com.practices.githubusers.ui.component.space.Space

@Composable
fun NoticeDialog(state: Failure?,
                 dismiss: () -> Unit
) {
    val showDialog = state != null

    if (showDialog) {
        Dialog(
            onDismissRequest = {
                dismiss.invoke()
            }
        ) {
            Card {
                Box(
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(16.dp)
                ) {
                    Column {
                        Text(state?.toErrorString() ?: "")
                        Space(20.dp)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                border = BorderStroke(1.dp, Color.Black),
                                onClick = {
                                    dismiss.invoke()
                                }) {
                                Text("Okay")
                            }
                        }

                    }

                }

            }
        }
    }
}