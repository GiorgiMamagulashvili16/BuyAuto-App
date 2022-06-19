package com.example.buyauto_app.presentation.profile_screen.di

import com.example.buyauto_app.presentation.profile_screen.ProfileFragment
import com.example.buyauto_app.presentation.profile_screen.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileScreenModule = module {
    scope<ProfileFragment> {
        viewModel { ProfileViewModel() }
    }
}