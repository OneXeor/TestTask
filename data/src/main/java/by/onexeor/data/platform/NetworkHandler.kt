package by.onexeor.data.platform

import android.annotation.TargetApi
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkInfo
import android.os.Build
import by.onexeor.data.di.scopes.PerApplication
import javax.inject.Inject


/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.testtask.internal.platform)
 */

@PerApplication
class NetworkHandler @Inject constructor(
    private val connectivityManager: ConnectivityManager
) {
    val isConnected get() = connectivityManager.isConnectedToInternet()
}

fun ConnectivityManager.isConnectedToInternet(): Boolean =
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
        isConnected(getNetworkCapabilities(activeNetwork))
    else {
        // TODO need to get rid of deprecated decision
        val networkInfo: NetworkInfo? = activeNetworkInfo
        networkInfo?.isConnected ?: false
    }

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun isConnected(networkCapabilities: NetworkCapabilities?): Boolean {
    return when (networkCapabilities) {
        null -> false
        else -> with(networkCapabilities) {
            hasTransport(TRANSPORT_CELLULAR) || hasTransport(
                TRANSPORT_WIFI
            )
        }
    }
}