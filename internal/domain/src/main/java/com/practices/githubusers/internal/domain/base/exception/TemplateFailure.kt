package com.practices.githubusers.internal.domain.base.exception

sealed class TemplateFailure(val error: Any?) {

    class FailureMessage(error: CharSequence?) : TemplateFailure(error)

    class FailureResources(error: Int?) : TemplateFailure(error)
}