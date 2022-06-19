package com.example.buyauto_app.presentation.auth_screen.di

import com.example.buyauto_app.data.repository.AuthRepositoryImpl
import com.example.buyauto_app.domain.repository.AuthRepository
import com.example.buyauto_app.domain.usecase.auth.AuthUseCase
import com.example.buyauto_app.domain.usecase.auth.AuthUseCaseImpl
import com.example.buyauto_app.domain.usecase.auth.AuthValidatorUseCase
import com.example.buyauto_app.presentation.auth_screen.AuthFragment
import com.example.buyauto_app.presentation.auth_screen.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authScreenModule = module {
    scope<AuthFragment> {
        factory<AuthRepository> {
            AuthRepositoryImpl(get(), get())
        }
        factory <AuthUseCase>{
            AuthUseCaseImpl(get())
        }
        factory { AuthValidatorUseCase(get()) }

        viewModel {
            AuthViewModel(get(),get())
        }

    }
}