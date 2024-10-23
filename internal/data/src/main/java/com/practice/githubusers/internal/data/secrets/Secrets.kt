package com.practice.githubusers.internal.data.secrets

import com.practices.githubusers.internal.data.secrets.ApiEndpointUrlImpl
import com.practices.githubusers.internal.data.secrets.ClientIdImpl
import com.practices.githubusers.internal.data.secrets.ClientSecretImpl

object Secrets {

    val apiEndpointUrl: String
        get() = ApiEndpointUrlImpl().value

    val clientId: String
        get() = ClientIdImpl().value

    val clientSecret: String
        get() = ClientSecretImpl().value
}