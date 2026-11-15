package com.example.android_1sem

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHandler(private val ctx: Context) {
    private val manager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun initNotifications() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannels(listOf(
                NotificationChannel(CH_MIN, "min", NotificationManager.IMPORTANCE_MIN),
                NotificationChannel(CH_DEFAULT, "default", NotificationManager.IMPORTANCE_DEFAULT),
                NotificationChannel(CH_HIGH, "high", NotificationManager.IMPORTANCE_HIGH)
            ))
        }
    }

    fun show(
        id: Int,
        title: String,
        body: String,
        importance: String,
        nonCancellable: Boolean
    ) {
        val channelId = when (importance) {
            "min"  -> CH_MIN
            "high" -> CH_HIGH
            else   -> CH_DEFAULT
        }

        val intent = Intent(ctx, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("notif_title", title)
            putExtra("notif_body", body)
        }
        val pi = PendingIntent.getActivity(
            ctx, id, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notif = NotificationCompat.Builder(ctx, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setContentIntent(pi)
            .setOngoing(nonCancellable)
            .setAutoCancel(!nonCancellable)
            .build()

        manager.notify(id, notif)
    }

    companion object {
        const val CH_MIN = "channel_min"
        const val CH_DEFAULT = "channel_default"
        const val CH_HIGH = "channel_high"
    }
}