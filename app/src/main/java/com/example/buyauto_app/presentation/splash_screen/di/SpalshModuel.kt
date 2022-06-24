package com.example.buyauto_app.presentation.splash_screen.di

import com.example.buyauto_app.presentation.splash_screen.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel {
        SplashViewModel(get())
    }
}