package com.example.buyauto_app.presentation.add_car_screen.di

import com.example.buyauto_app.data.repository.AddCarRepositoryImpl
import com.example.buyauto_app.domain.repository.AddCarRepository
import com.example.buyauto_app.domain.usecase.add_car.AddCarUseCase
import com.example.buyauto_app.domain.usecase.add_car.AddCarUseCaseImpl
import com.example.buyauto_app.domain.usecase.add_car.AddCarValidatorUseCase
import com.example.buyauto_app.presentation.add_car_screen.AddCarFragment
import com.example.buyauto_app.presentation.add_car_screen.AddCarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addCarScreenModule = module {
    scope<AddCarFragment> {
        viewModel {
            AddCarViewModel(get(), get())
        }

        factory<AddCarRepository> {
            AddCarRepositoryImpl(get(), get(), get())
        }
        factory<AddCarUseCase> {
            AddCarUseCaseImpl(get())
        }
        factory {
            AddCarValidatorUseCase(get())
        }
    }
}