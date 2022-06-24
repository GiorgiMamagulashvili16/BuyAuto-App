package com.example.buyauto_app.domain.usecase.add_car

import android.net.Uri
import com.example.buyauto_app.domain.model.CarItem
import com.example.buyauto_app.presentation.add_car_screen.AddCarScreenState

interface AddCarUseCase {
    suspend fun addCar(carItem: CarItem, imageList: List<Uri>): AddCarScreenState
}