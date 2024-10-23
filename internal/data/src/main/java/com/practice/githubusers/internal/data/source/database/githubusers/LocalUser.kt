package com.practice.githubusers.internal.data.source.database.githubusers

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import coil.request.ImageRequest
import com.practice.githubusers.internal.data.source.database.converter.DatabaseConverter
import com.practice.githubusers.internal.data.source.database.githubusers.LocalUser.Companion.TABLE_NAME
import com.practices.githubusers.internal.domain.user.model.UserItem

@Entity(tableName = TABLE_NAME)
class LocalUser(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val id: Int,
    @ColumnInfo(name = "user_name")
    val userName: String,
    @ColumnInfo(name = "avatar")
    val avatar: String,
    @ColumnInfo(name = "url")
    val url: String
) {

    companion object {
        const val TABLE_NAME = "github_users"

        fun mapOf(item: UserItem, context: Context, userResult: (LocalUser) -> Unit) {
            ImageRequest.Builder(context)
                .data(data = item.avatar).apply(
                    block = fun ImageRequest.Builder.() {
                        crossfade(true)
                    })
                .target { result ->
                    val bitmap = (result as? BitmapDrawable)?.bitmap
                    val bitmapBase64 = if (bitmap != null) {
                        DatabaseConverter.bitmapToBase64(bitmap)
                    } else ""

                    userResult.invoke(
                        LocalUser(
                            id = item.id,
                            userName = item.name,
                            avatar = bitmapBase64,
                            url = item.url
                        )
                    )
                }
                .build()

        }
    }
}