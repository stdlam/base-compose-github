package com.practices.githubusers.internal.domain.base.functional

import com.practices.githubusers.internal.domain.base.extensions.noId

data class Page<T>(
    var firstPage: Int,
    var currentPage: Int,
    var totalPages: Int,
    var nextPage: Int,
    var lastPage: Int,
    var totalRecords: Int,
    var totalUnseen: Int? = null,
    var pageSize: Int? = null,
    var lastItem: Boolean? = false,
    var lastId: Long? = null
) {
    var list: List<T> = mutableListOf()

    fun isEndPage(): Boolean = (nextPage == lastPage)

    fun isFirstPage(): Boolean = (currentPage == firstPage || firstPage == 0)

    fun canNextPage(): Boolean = (currentPage < totalPages)

    val listSize: Int
        get() = list.size

    companion object {
        fun <K> empty() = Page<K>(
            firstPage = 0,
            currentPage = 0,
            totalPages = 0,
            nextPage = 0,
            lastPage = 0,
            totalRecords = 0,
            totalUnseen = 0,
            lastItem = false,
            lastId = Long.noId()
        )
        const val PAGE_REACHED = -1
    }
}