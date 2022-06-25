package com.example.buyauto_app.domain.repository

import com.example.buyauto_app.data.model.UserDto
import com.example.buyauto_app.domain.model.CarItem
import com.example.buyauto_app.domain.util.Resource

interface GetCarRepository {

    suspend fun getAllCarsItem(): Resource<List<CarItem>>
    suspend fun getUserByUid(uid:String):Resource<UserDto>
}