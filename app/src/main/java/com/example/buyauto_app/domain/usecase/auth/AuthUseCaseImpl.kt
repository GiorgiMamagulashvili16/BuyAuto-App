package com.example.buyauto_app.domain.usecase.auth

import com.example.buyauto_app.domain.model.User
import com.example.buyauto_app.domain.repository.AuthRepository
import com.example.buyauto_app.domain.util.Resource
import com.example.buyauto_app.presentation.auth_screen.AuthScreenState
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthUseCaseImpl(private val authRepository: AuthRepository) : AuthUseCase {

    override suspend fun signUp(user: User):AuthScreenState{
        return when (val response = authRepository.signUp(user)) {
            is Resource.Success -> {
                AuthScreenState(isSignUpSuccess = true)
            }
            is Resource.Error -> {
                AuthScreenState(errorMessage = response.errorMessage)
            }
        }
    }

    override suspend fun logIn(email: String, password: String): AuthScreenState {
        return when (val response = authRepository.singIn(email, password)) {
            is Resource.Success -> {
                AuthScreenState(isLoginSuccess = true)
            }
            is Resource.Error -> {
                AuthScreenState(errorMessage = response.errorMessage)
            }
        }
    }

}