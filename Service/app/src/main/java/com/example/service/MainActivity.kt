package com.example.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playBtn: ImageView = findViewById<ImageView>(R.id.btnPlay)
        val stopBtn: ImageView = findViewById<ImageView>(R.id.btnStop)
        var flag: Boolean = true


        playBtn.setOnClickListener {
            val startIntent = Intent(this@MainActivity, MyService::class.java)
            startService(startIntent)
            if (flag) {
                playBtn.setImageResource(R.drawable.pause)
                flag = false
            } else {
                playBtn.setImageResource(R.drawable.play)
                flag = true
            }
        }

        stopBtn.setOnClickListener {
            val stopIntent = Intent(this@MainActivity, MyService::class.java)
            flag = true
            playBtn.setImageResource(R.drawable.play)
            stopService(stopIntent)
        }
    }
}