package com.practice.githubusers.internal.data.source.network.repository

import com.practice.githubusers.internal.data.source.network.response.ErrorMapper
import com.practice.githubusers.internal.data.source.network.response.ErrorResponseMapper
import com.practice.githubusers.internal.data.source.network.response.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.practices.githubusers.internal.domain.base.exception.Failure
import com.practices.githubusers.internal.domain.base.functional.Either.Left
import com.practices.githubusers.internal.domain.base.functional.Either.Right
import com.practices.githubusers.internal.domain.base.functional.Resource
import com.practices.githubusers.internal.domain.base.mapper.Mapper
import retrofit2.Call

abstract class NetworkRepository {

    open fun <T, R> safeNetworkFlow(call: Call<T>, transform: Mapper<T, R>) =
        safeNetworkFlow(call = call, transform = transform, defaultVal = null)

    open fun <T, R> safeNetworkFlow(call: Call<T>, transform: Mapper<T, R>, defaultVal: R?) =
        flow {
            emit(Resource.Loading)
            when (val rs = execute(call = call, transform = transform, defaultVal = defaultVal)) {
                is Left -> emit(Resource.Error(rs.a))
                is Right -> emit(Resource.Success(rs.b))
            }
        }.flowOn(Dispatchers.IO).catch { e ->
            emit(Resource.Error(Failure.UnknownFailure(e)))
        }

    open fun <T, R> safeCallFlow(call: Call<T>, transform: Mapper<T, R>) =
        safeCallFlow(call = call, transform = transform, defaultVal = null)

    open fun <T, R> safeCallFlow(call: Call<T>, transform: Mapper<T, R>, defaultVal: R?) =
        flow {
            when (val rs = execute(call = call, transform = transform, defaultVal = defaultVal)) {
                is Left -> emit(Left(rs.a))
                is Right -> emit(Right(rs.b))
            }
        }.flowOn(Dispatchers.IO).catch { e ->
            emit(Left(Failure.UnknownFailure(e)))
        }

    open fun <T, R> safeCall(call: Call<T>, transform: Mapper<T, R>) =
        safeCall(call = call, transform = transform, defaultVal = null)

    open fun <T, R> safeCall(call: Call<T>, transform: Mapper<T, R>, defaultVal: R?) =
        execute(call = call, transform = transform, defaultVal = defaultVal)

    private fun <T, R> execute(call: Call<T>, transform: Mapper<T, R>, defaultVal: R?) =
        try {
            when (val apiResponse = NetworkResponse.create(call.execute())) {
                is NetworkResponse.ApiSuccessResponse -> {
                    Right(transform.map(apiResponse.body))
                }
                is NetworkResponse.ApiEmptyResponse -> {
                    if (defaultVal == null) {
                        Left(com.practice.githubusers.internal.data.source.network.failure.ResponseFailure.SuccessEmptyResponse)
                    }
                    else Right(defaultVal)
                }
                is NetworkResponse.ApiErrorResponse -> {
                    Left(ErrorResponseMapper<T>().map(apiResponse))
                }
            }
        } catch (err: Throwable) {
            Left(ErrorMapper<T>().map(err))
        }
}