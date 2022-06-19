package com.example.buyauto_app.domain.repository

import com.example.buyauto_app.domain.model.User
import com.example.buyauto_app.domain.util.Resource
import com.google.firebase.auth.AuthResult

interface AuthRepository {

    suspend fun singIn(email: String, password: String): Resource<AuthResult>

    suspend fun signUp(user: User): Resource<AuthResult>
}