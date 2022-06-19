package com.example.buyauto_app.domain.usecase.auth

import com.example.buyauto_app.domain.model.User
import com.example.buyauto_app.presentation.auth_screen.AuthScreenState

interface AuthUseCase {
    suspend fun signUp(user: User): AuthScreenState
    suspend fun logIn(email: String, password: String): AuthScreenState
}