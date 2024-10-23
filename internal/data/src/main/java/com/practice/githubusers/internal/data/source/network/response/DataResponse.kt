package com.practice.githubusers.internal.data.source.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class DataResponse<T>(
    @property:Json(name = "statusCode") var status: Int? = null,
    @property:Json(name = "message") var message: DataMessage? = null,
    @property:Json(name = "data") var data: T? = null
)

@JsonClass(generateAdapter = true)
open class DataMessage(
    @Json(name = "code") var code: Int? = null,
    @Json(name = "text") var text: String? = null
)

@JsonClass(generateAdapter = true)
open class PageResultData<K>(
    @property:Json(name = "result") var result: K? = null,
    @property:Json(name = "metaData") var metaData: MetaDataResponse? = null
)