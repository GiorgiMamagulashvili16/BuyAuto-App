package com.example.buyauto_app.presentation.dashboard_screen.di

import com.example.buyauto_app.presentation.dashboard_screen.DashboardFragment
import com.example.buyauto_app.presentation.dashboard_screen.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashModule = module {
    scope<DashboardFragment> {
        viewModel { DashboardViewModel() }
    }
}