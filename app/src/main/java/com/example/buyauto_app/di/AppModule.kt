package com.example.buyauto_app.di

import com.example.buyauto_app.data.network.PushNotificationApi
import com.example.buyauto_app.domain.util.StringResourceProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        provideFirebaseAuth()
    }
    single {
        provideFirestore()
    }
    single {
        StringResourceProvider(androidContext())
    }
    single {
        provideStorage()
    }
    single {
        provideRetrofit()
    }
    single {
        provideNotificationService(get())
    }
}

fun provideFirestore() = FirebaseFirestore.getInstance()
fun provideFirebaseAuth() = FirebaseAuth.getInstance()
fun provideStorage() = Firebase.storage
fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl("https://fcm.googleapis.com").addConverterFactory(GsonConverterFactory.create()).build()
fun provideNotificationService(retrofit: Retrofit): PushNotificationApi = retrofit.create(PushNotificationApi::class.java)