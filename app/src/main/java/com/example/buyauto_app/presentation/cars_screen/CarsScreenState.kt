package com.example.buyauto_app.presentation.cars_screen

import com.example.buyauto_app.domain.model.CarItem

data class CarsScreenState(
    val allCarsData: List<CarItem>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)