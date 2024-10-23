package com.practices.githubusers.internal.domain.base.exception

sealed class Failure : Throwable() {

    class NetworkConnection : Failure()

    class TimeoutConnection : Failure()

    class SessionExpiredFailure : Failure()

    class UnknownFailure(val unknown: Throwable?) : Failure()

    class ServerError(val code: Int?, val error: String?) : Failure()

    /**
     * Extend this class for feature specific failures.
     */
    abstract class FeatureFailure : Failure()
}