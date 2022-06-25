package com.example.buyauto_app.presentation.profile_screen.di

import com.example.buyauto_app.data.repository.ProfileRepositoryImpl
import com.example.buyauto_app.domain.repository.ProfileRepository
import com.example.buyauto_app.domain.usecase.profile.ProfileUseCase
import com.example.buyauto_app.domain.usecase.profile.ProfileUseCaseImpl
import com.example.buyauto_app.presentation.profile_screen.ProfileFragment
import com.example.buyauto_app.presentation.profile_screen.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileScreenModule = module {
    scope<ProfileFragment> {
        viewModel { ProfileViewModel(get()) }

        factory<ProfileUseCase> {
            ProfileUseCaseImpl(get())
        }
        factory<ProfileRepository> {
            ProfileRepositoryImpl(get(), get())
        }
    }
}