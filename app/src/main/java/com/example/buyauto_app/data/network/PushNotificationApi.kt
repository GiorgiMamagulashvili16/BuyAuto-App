package com.example.buyauto_app.data.network

import com.example.buyauto_app.MainActivity.Companion.CONTENT_TYPE
import com.example.buyauto_app.MainActivity.Companion.SERVER_KEY
import com.example.buyauto_app.data.model.PushNotification
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface PushNotificationApi {

    @Headers("Authorization: key=${SERVER_KEY}", "Content-type:${CONTENT_TYPE}")
    @POST("fcm/send")
    suspend fun sendPush(
        @Body notification: PushNotification
    ): Response<ResponseBody>
}