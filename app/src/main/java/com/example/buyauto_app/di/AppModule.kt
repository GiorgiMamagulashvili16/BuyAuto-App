package com.example.buyauto_app.di

import com.example.buyauto_app.domain.util.StringResourceProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

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
}

fun provideFirestore() = FirebaseFirestore.getInstance()
fun provideFirebaseAuth() = FirebaseAuth.getInstance()
fun provideStorage() = Firebase.storage