package com.example.buyauto_app.domain.usecase.get_cars

import com.example.buyauto_app.presentation.cars_screen.CarsScreenState
import com.example.buyauto_app.presentation.profile_screen.ProfileScreenState

interface GetCarsUseCase {

    suspend fun getAllCars(): CarsScreenState
}