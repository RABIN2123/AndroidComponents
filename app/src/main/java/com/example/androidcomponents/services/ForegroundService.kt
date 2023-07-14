package com.example.androidcomponents.services

import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.androidcomponents.MainActivity
import com.example.androidcomponents.R

class ForegroundService : Service() {
    private lateinit var prefs: SharedPreferences
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        prefs = applicationContext.getSharedPreferences("savedId", Context.MODE_PRIVATE)
        when (intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }


    private fun start() {
        val notificationIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("state", true)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)
        }
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )
        val notification = NotificationCompat.Builder(this, "foreground_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("App in background")
            .setContentText("Open last window")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .setOngoing(true)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
    }


    enum class Actions {
        START, STOP
    }
}