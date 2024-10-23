package com.practices.githubusers.internal.data.secrets

import com.practice.githubusers.internal.data.secrets.ApiEndpointUrl

class ApiEndpointUrlImpl: ApiEndpointUrl {

    override val value: String
        get() = "https://api.github.com/"
}