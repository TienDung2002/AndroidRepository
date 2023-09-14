package com.example.service

import android.util.Log
import android.os.Build
import android.os.IBinder
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.graphics.BitmapFactory
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import android.app.PendingIntent


class MyService : Service() {
    private var myMusic: MediaPlayer? = null
    private var notificationBuilder: NotificationCompat.Builder? = null

    companion object {
        const val CHANNEL_ID = "channel_running"
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("")
    }

    // Run app
    override fun onCreate() {
        super.onCreate()
        myMusic = MediaPlayer.create(this, R.raw.light_it_up)
        myMusic?.isLooping = true
        notificationBuilder = NotificationCompat.Builder(baseContext, CHANNEL_ID)
        createNotificationChannel()
        showNotification()
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notifyChannel = NotificationChannel(
                CHANNEL_ID,
                "Now playing Song",
                NotificationManager.IMPORTANCE_HIGH
            )
            notifyChannel.description = "Important notify!"

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(notifyChannel)
        }
    }

    private fun showNotification() {
        // Pending intent cho nút pause ở notification
        val pauseIntent = Intent(this@MyService, MyService::class.java)
        pauseIntent.action = "PAUSE_MUSIC"
        val pendingPauseIntent = PendingIntent.getService(
            this@MyService,
            0,
            pauseIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        // Chuyển ảnh ra bitmap
        val bitmapImg = BitmapFactory.decodeResource(resources, R.drawable.notify_large_ic)
//        val mediaSession = MediaSessionCompat(this, "tag")
        val notification = NotificationCompat.Builder(baseContext, CHANNEL_ID)
            .setContentTitle("Light it up - remix")
            .setContentText("Music are now playing")
            .setSmallIcon(R.drawable.notify_icon)
            .setLargeIcon(bitmapImg)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            // Action
            .addAction(R.drawable.previous, "Previous", null)           // 0
            .addAction(R.drawable.pause_compat, "Pause", pendingPauseIntent)  // 1
            .addAction(R.drawable.next, "Next", null)                   // 2
            .addAction(R.drawable.close, "Exit", null)                  // 3
            // Apply the media style template
//            .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
//                .setMediaSession(mediaSession.sessionToken)
//                .setShowActionsInCompactView(1)) /* #1: pause button \*
            .build()
        startForeground(13, notification)
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Pause ở notification
        if (intent?.action == "PAUSE_MUSIC") {
            if (myMusic != null && myMusic!!.isPlaying) {
                myMusic!!.pause()
                Log.d("MusicService", "Music has stopped")
            }
        } else { // pause trong ứng dụng
            if (myMusic!!.isPlaying) {
                myMusic?.pause()
                Log.d("MusicService", "Music has stopped")
            } else {
                myMusic?.start()
                Log.d("MusicService", "Music is playing")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MusicService", "Music has stopped")
        myMusic?.release()
    }
}