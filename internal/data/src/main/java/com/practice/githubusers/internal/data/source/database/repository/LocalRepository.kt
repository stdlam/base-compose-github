package com.practice.githubusers.internal.data.source.database.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// This LocalRepository is not used because this app doesn't use local database anymore.
abstract class LocalRepository {
    fun asyncDataLocal(
        query: suspend CoroutineScope.(Int) -> Unit,
        onError: (Throwable?) -> Unit
    ): Job {
        return CoroutineScope(Dispatchers.Default).asyncDatabase(
            query,
            onError = { onError(it) }
        )
    }

    private fun CoroutineScope.asyncDatabase(
        query: suspend CoroutineScope.(Int) -> Unit,
        onError: (Throwable?) -> Unit
    ): Job {

        return launch {
            try {
                query(1)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}