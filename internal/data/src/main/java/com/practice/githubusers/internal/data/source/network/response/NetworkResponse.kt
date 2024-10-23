package com.practice.githubusers.internal.data.source.network.response

import retrofit2.HttpException
import retrofit2.Response

sealed class NetworkResponse<T> {

    companion object {
        const val HTTP_UNSET = -1

        fun <T> create(throwable: Throwable): ApiErrorResponse<T> {
            var httpCode: Int = HTTP_UNSET
            val errorBody = when(throwable) {
                is HttpException -> {
                    val response = throwable.response()
                    httpCode = response?.code() ?: HTTP_UNSET
                    response?.errorBody()?.string()?.let {
                        ErrorBody.fromJson(it)
                    } ?: ErrorBody.empty
                }
                else -> ErrorBody.empty
            }
            return ApiErrorResponse(errorBody, httpCode)
        }

        fun <T> create(response: Response<T>): NetworkResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                val httpCode = response.code()
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    ErrorBody.fromMessage(response.message())
                } else {
                    ErrorBody.fromJson(msg)
                }
                ApiErrorResponse(errorMsg, httpCode)
            }
        }
    }

    class ApiEmptyResponse<T> : NetworkResponse<T>()

    data class ApiSuccessResponse<T>(val body: T) : NetworkResponse<T>()

    data class ApiErrorResponse<T>(val error: ErrorBody, val httpCode: Int) : NetworkResponse<T>()
}
