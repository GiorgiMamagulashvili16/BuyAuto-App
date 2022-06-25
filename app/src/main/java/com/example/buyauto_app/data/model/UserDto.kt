package com.example.buyauto_app.data.model

import com.example.buyauto_app.domain.model.User

data class UserDto(
    val uid: String? = null,
    val username: String? = null,
    val email: String? = null,
){
    fun toDomain() = User(uid,username!!,email!!)
}
