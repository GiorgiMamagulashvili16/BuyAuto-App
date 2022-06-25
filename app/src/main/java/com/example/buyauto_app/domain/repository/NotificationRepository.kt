package com.example.buyauto_app.domain.repository

import com.example.buyauto_app.data.model.NotificationData

interface NotificationRepository {

    suspend fun sendNotification(notification: NotificationData, receiverId: String)

}