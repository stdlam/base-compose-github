package com.practice.githubusers.internal.data.source.network.response

import com.practices.githubusers.internal.domain.base.exception.Failure
import com.practices.githubusers.internal.domain.base.mapper.Mapper

internal class ErrorResponseMapper<T> : Mapper<NetworkResponse.ApiErrorResponse<T>, Failure> {

    override fun map(from: NetworkResponse.ApiErrorResponse<T>): Failure {
        return when (from.httpCode) {
            401, 403 -> Failure.SessionExpiredFailure()
            504 -> Failure.NetworkConnection()
            else -> Failure.ServerError(
                code = from.error.message.code,
                error = from.error.message.text
            )
        }
    }
}