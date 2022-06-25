package com.example.buyauto_app.presentation.profile_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buyauto_app.domain.usecase.profile.ProfileUseCase
import com.example.buyauto_app.domain.util.extensions.execute

class ProfileViewModel(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    private val _screenState = MutableLiveData<ProfileScreenState>()
    val screenState: LiveData<ProfileScreenState> = _screenState

    fun setCarList() = execute {
        _screenState.postValue(ProfileScreenState(isLoading = true))
        _screenState.postValue(profileUseCase.getCurrentUserItems())
    }

    fun logout() = execute {
        _screenState.postValue(ProfileScreenState(isLoading = true))
        _screenState.postValue(profileUseCase.logOut())
    }
}