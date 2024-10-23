package com.practices.githubusers.internal.domain.base

import kotlinx.coroutines.flow.Flow
import com.practices.githubusers.internal.domain.base.functional.Resource

abstract class ResourceUseCase<out Type, in Params> where Type : Any {

    abstract fun execute(params: Params): Flow<Resource<Type>>

    operator fun invoke(params: Params): Flow<Resource<Type>> = execute(params)

    open fun onCleared() {}

    class None
}