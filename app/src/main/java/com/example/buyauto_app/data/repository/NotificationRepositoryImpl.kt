package com.example.buyauto_app.data.repository

import android.util.Log
import com.example.buyauto_app.data.model.NotificationData
import com.example.buyauto_app.data.model.PushNotification
import com.example.buyauto_app.data.network.PushNotificationApi
import com.example.buyauto_app.domain.repository.NotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotificationRepositoryImpl(
    private val serviceApi: PushNotificationApi,
) : NotificationRepository {
    override suspend fun sendNotification(notification: NotificationData, receiverId: String) {
        return withContext(Dispatchers.IO) {
            val response = serviceApi.sendPush(
                PushNotification(
                    notification, "/topics/$receiverId"
                )
            )
            if (response.isSuccessful) {
                val body = response.body()!!
                Log.d("RESPONSE", "$body")
            } else {
                Log.d("RESPONSE", "${response.errorBody()}")
            }
        }
    }
}