package com.meduardaqb.wooflove.util

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.support.v7.app.NotificationCompat
import com.meduardaqb.wooflove.R


class LocalPushNotification : PushNotification {

    override fun sendNotification(context: Context, message: String) {

        val notification = NotificationCompat.Builder(context)

        notification.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(message)
                .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_SOUND)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification.build())
    }
}