package com.example.buyauto_app.presentation.car_details.di

import com.example.buyauto_app.data.repository.NotificationRepositoryImpl
import com.example.buyauto_app.domain.repository.NotificationRepository
import com.example.buyauto_app.presentation.car_details.CarDetailsFragment
import com.example.buyauto_app.presentation.car_details.CarDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val carDetailModule = module {
    scope<CarDetailsFragment> {
        viewModel { CarDetailsViewModel(get()) }

        factory<NotificationRepository> {
            NotificationRepositoryImpl(get())
        }
    }
}