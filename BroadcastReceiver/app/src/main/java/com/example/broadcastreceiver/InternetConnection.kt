package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.widget.Toast
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager
import android.util.Log


class InternetConnection : BroadcastReceiver() {
    private fun showToast(context: Context?, message: String) {
        context.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == WifiManager.WIFI_STATE_CHANGED_ACTION) {
            val wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1)
            when (wifiState) {
                WifiManager.WIFI_STATE_ENABLED -> {
                    Log.d("Internet", "Internet is on")
                    showToast(context, "Internet is now on")
                }

                WifiManager.WIFI_STATE_DISABLED -> {
                    Log.d("Internet", "Internet is off")
                    showToast(context, "Internet is off")
                }
            }
        }


//        when (intent?.action) {
//            // Check Wifi
//            WifiManager.WIFI_STATE_CHANGED_ACTION -> {
//                val wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1)
//                when (wifiState) {
//                    WifiManager.WIFI_STATE_ENABLED -> {
//                        Log.d("Internet", "Wifi is on")
//                        showToast(context, "Wifi is now on")
//                    }
//
//                    WifiManager.WIFI_STATE_DISABLED -> {
//                        Log.d("Internet", "Wifi is off")
//                        showToast(context, "Wifi is off")
//                    }
//                }
//            }
//            // Check data mobile
//            TelephonyManager.ACTION_PHONE_STATE_CHANGED -> {
//                val telephonyManager =
//                    context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//                val dataState = telephonyManager.dataState
//                when (dataState) {
//                    TelephonyManager.DATA_CONNECTED -> {
//                        Log.d("Mobile data", "Mobile data is on")
//                        showToast(context, "Mobile data is now on")
//                    }
//
//                    TelephonyManager.DATA_DISCONNECTED -> {
//                        Log.d("Mobile data", "Mobile data is off")
//                        showToast(context, "Mobile data is off")
//                    }
//                }
//            }
//        }
    }
}
