package com.example.buyauto_app.presentation.splash_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buyauto_app.domain.util.extensions.execute
import com.google.firebase.auth.FirebaseAuth

class SplashViewModel(
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _isUserLogged = MutableLiveData<Boolean>()
    val isUserLogged: LiveData<Boolean> = _isUserLogged

    fun checkIsUserLogged() {
        execute {
            _isUserLogged.postValue(auth.currentUser != null)
        }
    }
}