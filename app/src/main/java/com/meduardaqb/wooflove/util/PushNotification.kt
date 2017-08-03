package com.meduardaqb.wooflove.util

import android.content.Context

interface PushNotification {
    fun sendNotification(context: Context, message: String)
}