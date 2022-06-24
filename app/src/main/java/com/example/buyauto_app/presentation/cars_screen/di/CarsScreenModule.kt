package com.example.buyauto_app.presentation.cars_screen.di

import com.example.buyauto_app.data.repository.GetCarsRepositoryImpl
import com.example.buyauto_app.domain.repository.GetCarRepository
import com.example.buyauto_app.domain.usecase.get_cars.GetCarsUseCase
import com.example.buyauto_app.domain.usecase.get_cars.GetCarsUseCaseImpl
import com.example.buyauto_app.presentation.cars_screen.CarsFragment
import com.example.buyauto_app.presentation.cars_screen.CarsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val carsScreenModule = module {
    scope<CarsFragment> {

        viewModel {
            CarsViewModel(get())
        }
        factory<GetCarRepository> {
            GetCarsRepositoryImpl(get(), get())
        }
        factory<GetCarsUseCase> {
            GetCarsUseCaseImpl(get())
        }
    }
}