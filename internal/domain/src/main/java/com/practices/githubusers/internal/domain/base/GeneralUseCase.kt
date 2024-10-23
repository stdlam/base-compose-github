package com.practices.githubusers.internal.domain.base

import kotlinx.coroutines.flow.Flow
import com.practices.githubusers.internal.domain.base.exception.Failure
import com.practices.githubusers.internal.domain.base.functional.Either

abstract class GeneralUseCase<out Type, in Params> where Type : Any {

    abstract fun execute(params: Params): Flow<Either<Failure, Type>>

    operator fun invoke(params: Params): Flow<Either<Failure, Type>> = execute(params)

    open fun onCleared() {}

    class None
}