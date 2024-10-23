package com.practice.githubusers.internal.data.source.network.response

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName

data class ErrorBody(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: ErrorBodyData
) {
    companion object {
        val empty = ErrorBody(0, ErrorBodyData.empty)

        fun fromMessage(message: String) = ErrorBody(0, ErrorBodyData(null, message))

        fun fromJson(data: String): ErrorBody {
            return try {
                val json = GsonBuilder().serializeNulls().create()
                json.fromJson(data, ErrorBody::class.java)
            } catch (je: Exception) {
                empty
            }
        }
    }
}

data class ErrorBodyData(
    @SerializedName("code") val code: Int?,
    @SerializedName("text") val text: String?
) {
    companion object {
        val empty = ErrorBodyData(null, null)
    }
}