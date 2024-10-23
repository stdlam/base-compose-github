package com.practice.githubusers.internal.data.source.network.interceptor

import android.content.Context
import com.practice.githubusers.internal.data.source.network.provider.NetworkUtils
import okhttp3.Request

class CachingHeaderInterceptor(private val context: Context) : NetworkInterceptor() {

    override fun applyOptions(builder: Request.Builder) {
        builder.addHeader("Content-Type", "application/json; charset=utf-8")
        if (NetworkUtils.hasNetworkConnection(context)) {
            /*  If there is Internet, get the cache that was stored 5 seconds ago.
            *  If the cache is older than 5 seconds, then discard it,
            *  and indicate an error in fetching the response.
            *  The 'max-age' attribute is responsible for this behavior.
            */
            builder.addHeader("Cache-Control", "public, max-age=" + 5).build()
        } else {
            /*
            *  If there is no Internet, get the cache that was stored 7 days ago.
            *  If the cache is older than 7 days, then discard it,
            *  and indicate an error in fetching the response.
            *  The 'max-stale' attribute is responsible for this behavior.
            *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
            */
            builder.addHeader("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
        }
    }
}