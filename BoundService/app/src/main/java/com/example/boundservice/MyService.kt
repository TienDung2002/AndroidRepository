package com.example.boundservice

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): MyService = this@MyService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun sum(num1: Float, num2: Float): Float = num1 + num2
}