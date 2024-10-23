package com.practice.githubusers.internal.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MetaDataResponse (
    @Json(name = "currentPage") val currentPage: Int,
    @Json(name = "firstPage") val firstPage: Int?,
    @Json(name = "lastPage") val lastPage: Int?,
    @Json(name = "nextPage") val nextPage: Int?,
    @Json(name = "pageSize") val pageSize: Int?,
    @Json(name = "previousPage") val previousPage: Int?,
    @Json(name = "totalRecords") val totalRecords: Int?,
    @Json(name = "totalPages") val totalPages: Int?,
)
