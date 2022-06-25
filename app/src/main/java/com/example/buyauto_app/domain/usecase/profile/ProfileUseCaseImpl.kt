package com.example.buyauto_app.domain.usecase.profile

import com.example.buyauto_app.domain.repository.ProfileRepository
import com.example.buyauto_app.domain.util.Resource
import com.example.buyauto_app.presentation.profile_screen.ProfileScreenState

class ProfileUseCaseImpl(
    private val profileRepository: ProfileRepository
) : ProfileUseCase {
    override suspend fun getCurrentUserItems(): ProfileScreenState =
        when (val response = profileRepository.getCurrentUserCarList()) {
            is Resource.Success -> {
                ProfileScreenState(data = response.data)
            }
            is Resource.Error -> {
                ProfileScreenState(errorMessage = response.errorMessage)
            }
        }

    override suspend fun logOut(): ProfileScreenState =
        when (val response = profileRepository.logOut()) {
            is Resource.Success -> {
                ProfileScreenState(isLogOuted = true)
            }
            is Resource.Error -> {
                ProfileScreenState(errorMessage = response.errorMessage)
            }
        }

}