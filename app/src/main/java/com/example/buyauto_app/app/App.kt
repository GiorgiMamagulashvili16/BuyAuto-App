package com.example.buyauto_app.app

import android.app.Application
import com.example.buyauto_app.di.appModule
import com.example.buyauto_app.presentation.add_car_screen.di.addCarScreenModule
import com.example.buyauto_app.presentation.auth_screen.di.authScreenModule
import com.example.buyauto_app.presentation.car_details.di.carDetailModule
import com.example.buyauto_app.presentation.cars_screen.di.carsScreenModule
import com.example.buyauto_app.presentation.dashboard_screen.di.dashModule
import com.example.buyauto_app.presentation.favorites_screen.di.favoritesScreenModule
import com.example.buyauto_app.presentation.profile_screen.di.profileScreenModule
import com.example.buyauto_app.presentation.splash_screen.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App :Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule, authScreenModule, dashModule, carsScreenModule,
                    addCarScreenModule, favoritesScreenModule, profileScreenModule, splashModule,
                    carDetailModule
                )
            )
        }
    }
}