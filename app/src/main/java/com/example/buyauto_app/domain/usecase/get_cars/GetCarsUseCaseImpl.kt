package com.example.buyauto_app.domain.usecase.get_cars

import com.example.buyauto_app.domain.repository.GetCarRepository
import com.example.buyauto_app.domain.util.Resource
import com.example.buyauto_app.presentation.cars_screen.CarsScreenState
import com.example.buyauto_app.presentation.profile_screen.ProfileScreenState

class GetCarsUseCaseImpl(
    private val getCarsRepository: GetCarRepository
) : GetCarsUseCase {
    override suspend fun getAllCars(): CarsScreenState {
        return when (val response = getCarsRepository.getAllCarsItem()) {
            is Resource.Success -> {
                CarsScreenState(allCarsData = response.data)
            }
            is Resource.Error -> {
                CarsScreenState(errorMessage = response.errorMessage)
            }
        }
    }
}