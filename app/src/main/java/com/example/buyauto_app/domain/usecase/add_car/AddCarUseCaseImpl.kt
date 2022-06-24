package com.example.buyauto_app.domain.usecase.add_car

import android.net.Uri
import com.example.buyauto_app.domain.model.CarItem
import com.example.buyauto_app.domain.repository.AddCarRepository
import com.example.buyauto_app.domain.util.Resource
import com.example.buyauto_app.presentation.add_car_screen.AddCarScreenState

class AddCarUseCaseImpl(
    private val addCarRepository: AddCarRepository
) : AddCarUseCase {
    override suspend fun addCar(carItem: CarItem, imageList: List<Uri>): AddCarScreenState {
        return when (val response = addCarRepository.addCarItem(carItem, imageList)) {
            is Resource.Success -> {
                AddCarScreenState(isSuccessFull = true)
            }
            is Resource.Error -> {
                AddCarScreenState(errorMessage = response.errorMessage)
            }
        }
    }
}