package com.example.buyauto_app.presentation.auth_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buyauto_app.domain.model.User
import com.example.buyauto_app.domain.usecase.auth.AuthUseCase
import com.example.buyauto_app.domain.usecase.auth.AuthValidatorUseCase
import com.example.buyauto_app.domain.util.extensions.execute

class AuthViewModel(
    private val authUseCase: AuthUseCase,
    private val authValidator: AuthValidatorUseCase
) : ViewModel() {

    private val _authScreenState = MutableLiveData<AuthScreenState>()
    val authScreenState : LiveData<AuthScreenState> = _authScreenState

    fun signUp(
        email: String,
        password: String,
        repeatPassword: String,
        username: String
    ) {
        execute {
            _authScreenState.postValue(AuthScreenState(isLoading = true))
            val user = User(username = username, email = email, password = password)
            authValidator.validateSignUp(user,repeatPassword).also {
                if (it.success) {
                    _authScreenState.postValue(authUseCase.signUp(user))

                } else if (it.errorMess != null){
                    _authScreenState.postValue(AuthScreenState(errorMessage = it.errorMess))
                }
            }
        }
    }

    fun signIn(email: String, password: String) {
        execute {
            _authScreenState.postValue(AuthScreenState(isLoading = true))
            authValidator.validateSignIn(email, password).also {
                if (it.success) {
                    _authScreenState.postValue(authUseCase.logIn(email, password))
                } else {
                    _authScreenState.postValue(AuthScreenState(errorMessage = it.errorMess))
                }
            }
        }
    }
}