package com.example.buyauto_app.data.model

data class NotificationData(
    val senderId: String,
    val title: String,
    val message: String
)
data class PushNotification(
    val data: NotificationData,
    val to: String
)