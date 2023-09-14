package com.example.boundservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var sumService: MyService? = null
    private var isServiceBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val eT_1: EditText = findViewById(R.id.ed_1)
        val eT_2: EditText = findViewById(R.id.ed_2)
        var sumResult: TextView = findViewById(R.id.sumResult)
        val sumBtn: Button = findViewById(R.id.sumBtn)
        val stopService: Button = findViewById(R.id.stopSv)
        val serIntent = Intent(this@MainActivity, MyService::class.java)

        sumBtn.setOnClickListener {
            val num1 = eT_1.text.toString().toFloat()
            val num2 = eT_2.text.toString().toFloat()

            if (isServiceBound) {
                val result = sumService?.sum(num1, num2)
//                Log.d("MyService", "Sum result: $result")
                sumResult.text = result.toString()
            }
            bindService(serIntent, serviceConnection, Context.BIND_AUTO_CREATE)
            Toast.makeText(this@MainActivity, "Nhấn thêm lần nữa để hiện kết quả!", Toast.LENGTH_SHORT).show()
        }

        stopService.setOnClickListener {
            onDestroy()
            finish()
        }
    }

    // Kết thúc service
    override fun onDestroy() {
        super.onDestroy()
        if (isServiceBound) {
            unbindService(serviceConnection)
            isServiceBound = false
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyService.LocalBinder
            sumService = binder.getService()
            isServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isServiceBound = false
            sumService = null
        }
    }
}