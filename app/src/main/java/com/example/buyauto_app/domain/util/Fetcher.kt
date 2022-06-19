package com.example.buyauto_app.domain.util

inline fun <T> fetchData(call: () -> Resource<T>): Resource<T> {
    return try {
        call.invoke()
    } catch (e: Exception) {
        Resource.Error(e.message!!)
    }
}