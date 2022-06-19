package com.example.buyauto_app.presentation.add_car_screen.di

import com.example.buyauto_app.presentation.add_car_screen.AddCarFragment
import com.example.buyauto_app.presentation.add_car_screen.AddCarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addCarScreenModule = module {
    scope<AddCarFragment> {
        viewModel {
            AddCarViewModel()
        }
    }
}