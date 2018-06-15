package com.raccoon.flags

import android.content.Context
import android.net.ConnectivityManager
import java.net.InetAddress
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectionManager
@Inject
constructor(private val context: Context) {

    fun isConnectionAvailable() = isNetworkConnected() && isInternetAvailable()

    private fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    private fun isInternetAvailable() =
            try {
                InetAddress.getByName("google.com").toString().isNotEmpty()
            } catch (ex: Exception) {
                false
            }

}