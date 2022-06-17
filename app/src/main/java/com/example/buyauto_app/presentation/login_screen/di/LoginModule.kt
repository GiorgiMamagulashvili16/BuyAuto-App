package com.example.buyauto_app.presentation.login_screen.di

import com.example.buyauto_app.presentation.login_screen.AuthFragment
import com.example.buyauto_app.presentation.login_screen.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authScreenModule = module {
    scope<AuthFragment> {
        viewModel {
            AuthViewModel()
        }
    }
}