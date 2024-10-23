package com.practices.githubusers.internal.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import com.practices.githubusers.internal.domain.base.exception.Failure
import com.practices.githubusers.internal.domain.base.functional.Either

/**
 * Thank @[Fernando Cejas](https://github.com/android10)
 */
abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        onRevoke: (Either<Failure, Type>) -> Unit = {}
    ) {
        scope.launch {
            val deferred = async(Dispatchers.IO) {
                run(params)
            }
            onRevoke(deferred.await())
        }
    }

    open fun onCleared() {}

    class None
}