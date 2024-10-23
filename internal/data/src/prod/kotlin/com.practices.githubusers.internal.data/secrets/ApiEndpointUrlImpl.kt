package com.practices.githubusers.internal.data.secrets

class ApiEndpointUrlImpl: ApiEndpointUrl {

    override val value: String
        get() = "https://api.github.com/"
}