package com.example.buyauto_app.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.buyauto_app.R
import kotlin.random.Random

class BatteryReceiver : BroadcastReceiver() {

    override fun onReceive(ctx: Context?, intent: Intent?) {
        val level = intent?.getIntExtra("level", 0)
        if (level != 0 && level!! % 10 == 0) {
            createNotification("your battery is $level %", ctx!!)
        }
    }

    private fun createNotification(message: String, context: Context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(TITLE)
            .setContentText(message)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.car_logo_img)
            .build()

        notificationManager.notify(1, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channelName = CHANNEL_NAME
        val channel = NotificationChannel(
            CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = CHANNEL_DESC
            enableLights(true)
            lightColor = Color.YELLOW
        }
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        private const val CHANNEL_ID = "broadcast_id"
        private const val CHANNEL_DESC = "broadcast notification channel"
        private const val CHANNEL_NAME = "broadcast channel"
        private const val TITLE = "Battery level"
    }
}