package com.practice.githubusers.internal.data.di.scopes

import javax.inject.Qualifier

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CachingHeaderInterceptor