package com.practice.githubusers.internal.data.source.network.response

import com.squareup.moshi.JsonClass
import com.practices.githubusers.internal.domain.base.mapper.Mapper

@JsonClass(generateAdapter = true)
open class EmptyResponse : DataResponse<Any>()

class SuccessMapper: Mapper<EmptyResponse, Boolean> {
    override fun map(from: EmptyResponse): Boolean {
        return from.status == 200 || from.status == 201
    }
}