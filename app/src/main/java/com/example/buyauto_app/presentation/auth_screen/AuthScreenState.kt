package com.example.buyauto_app.presentation.auth_screen

data class AuthScreenState(
    val isLoginSuccess: Boolean = false,
    val isSignUpSuccess: Boolean = false,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
