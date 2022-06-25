package com.example.buyauto_app.presentation.profile_screen

import com.example.buyauto_app.domain.model.CarItem

data class ProfileScreenState(
    val data: List<CarItem>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLogOuted: Boolean = false
)
