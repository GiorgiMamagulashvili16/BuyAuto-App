package com.example.buyauto_app.presentation.add_car_screen

data class AddCarScreenState(
    val isSuccessFull: Boolean = false,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
