package com.example.buyauto_app.domain.model

import com.example.buyauto_app.data.data.UserDto

data class User(
    var uid: String? = null,
    val username: String,
    val email: String,
    val password: String? = null
) {
    fun toDto() = UserDto(uid!!, username, email)
}
