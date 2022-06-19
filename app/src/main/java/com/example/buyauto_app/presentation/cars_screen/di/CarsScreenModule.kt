package com.example.buyauto_app.presentation.cars_screen.di

import com.example.buyauto_app.presentation.cars_screen.CarsFragment
import com.example.buyauto_app.presentation.cars_screen.CarsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val carsScreenModule = module {
    scope<CarsFragment> {
        viewModel {
            CarsViewModel()
        }
    }
}