package com.example.buyauto_app.domain.usecase.auth

data class ValidatorResult(
    val success: Boolean = true,
    val errorMess: String? = null
)
