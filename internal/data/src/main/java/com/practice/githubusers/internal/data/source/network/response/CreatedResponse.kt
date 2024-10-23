package com.practice.githubusers.internal.data.source.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.practices.githubusers.internal.domain.base.extensions.noId
import com.practices.githubusers.internal.domain.base.extensions.orNoId
import com.practices.githubusers.internal.domain.base.mapper.Mapper

@JsonClass(generateAdapter = true)
class CreatedResponse : DataResponse<CreatedResultData>()

@JsonClass(generateAdapter = true)
class CreatedResultData(@Json(name = "id") var id: Long?)

class CreatedMapper: Mapper<CreatedResponse, Long> {
    override fun map(from: CreatedResponse): Long {
        return if (from.status == 200 || from.status == 201) {
            from.data?.id.orNoId()
        } else Long.noId()
    }
}