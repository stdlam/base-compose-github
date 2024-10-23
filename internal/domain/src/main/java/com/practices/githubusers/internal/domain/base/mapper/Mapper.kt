package com.practices.githubusers.internal.domain.base.mapper

interface Mapper<in L, out R> {
    fun map(from: L): R
}