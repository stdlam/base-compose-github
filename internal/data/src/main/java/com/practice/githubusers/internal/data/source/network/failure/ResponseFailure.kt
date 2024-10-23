package com.practice.githubusers.internal.data.source.network.failure

import com.practices.githubusers.internal.domain.base.exception.Failure.FeatureFailure

sealed class ResponseFailure {

    object SuccessEmptyResponse : FeatureFailure() {
        private fun readResolve(): Any =
            SuccessEmptyResponse
    }
}