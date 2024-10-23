package com.practice.githubusers.internal.data.source.network.response

import com.practices.githubusers.internal.domain.base.exception.Failure
import com.practices.githubusers.internal.domain.base.mapper.Mapper
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

internal class ErrorMapper<T> : Mapper<Throwable, Failure> {

    override fun map(from: Throwable): Failure {
        return when (from) {
            is HttpException -> {
                val errorHttp = NetworkResponse.create<T>(from)
                when (errorHttp.httpCode) {
                    401, 403 -> Failure.SessionExpiredFailure()
                    504 -> Failure.NetworkConnection()
                    else -> Failure.ServerError(
                        code = errorHttp.error.message.code,
                        error = errorHttp.error.message.text
                    )
                }
            }
            is SocketTimeoutException,
            is UnknownHostException,
            is InterruptedIOException -> Failure.NetworkConnection()
            is TimeoutException -> Failure.TimeoutConnection()
            else -> Failure.UnknownFailure(from)
        }
    }
}