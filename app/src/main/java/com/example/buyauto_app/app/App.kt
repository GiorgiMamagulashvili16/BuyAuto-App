package com.example.buyauto_app.app

import android.app.Application
import com.example.buyauto_app.di.appModule
import com.example.buyauto_app.presentation.auth_screen.di.authScreenModule
import com.example.buyauto_app.presentation.dashboard_screen.di.dashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App :Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule,authScreenModule, dashModule))
        }
    }
}