package com.jesusrojo.pagingdemo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

// needs  MANIFEST   <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
class InternetHelp {

    companion object {
//        fun isNetworkNotAvailableShowMessage(context: Context?,
//                                             containerView: SwipeRefreshLayout?): Boolean {
//            if(!isNetworkAvailable(context)){
//                showSnackBarNoInternet(containerView)
//                return false
//            }
//            return true
//        }

        fun isNetworkAvailable(context: Context?): Boolean {
            if (context == null) return false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    when {
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                    }
                }
            } else {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true
                }
            }
            return false
        }

//        private fun showSnackBarNoInternet(containerView: SwipeRefreshLayout?) {
//            containerView?.let {
//                val snack = Snackbar.make(containerView,
//                    R.string.error_no_internet,
//                    Snackbar.LENGTH_SHORT)
//                snack.show()
//            }
//        }

/////////////WITH ANDROID  android.R.id.content
//         fun showSnackBarNoInternet(act: Activity?) {
//             act?.let {
//                 val rootView: View = act.findViewById(android.R.id.content)
//                 val snack = Snackbar.make(rootView, R.string.error_no_internet,
//                     Snackbar.LENGTH_SHORT)
//                 snack.show()
//             }
//         }
////////////
    }
}