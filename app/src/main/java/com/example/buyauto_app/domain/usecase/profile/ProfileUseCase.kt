package com.example.buyauto_app.domain.usecase.profile

import com.example.buyauto_app.presentation.profile_screen.ProfileScreenState

interface ProfileUseCase {

    suspend fun getCurrentUserItems(): ProfileScreenState
    suspend fun logOut():ProfileScreenState
}