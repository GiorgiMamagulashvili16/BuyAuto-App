package com.example.buyauto_app.domain.repository

import com.example.buyauto_app.domain.model.CarItem
import com.example.buyauto_app.domain.model.User
import com.example.buyauto_app.domain.util.Resource

interface ProfileRepository {

    suspend fun getCurrentUserCarList(): Resource<List<CarItem>>
    suspend fun getCurrentUser():Resource<User>
    suspend fun logOut():Resource<Unit>
}