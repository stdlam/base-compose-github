package com.practice.githubusers.internal.data.source.network.provider

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkUtils {
    fun hasNetworkConnection(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val nw = connectivityManager?.activeNetwork
        val actNwc = connectivityManager?.getNetworkCapabilities(nw) ?: return false
        return when {
            actNwc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNwc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //for other device how are able to connect with Ethernet
            actNwc.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            //for check internet over Bluetooth
            //actNwc.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }
}