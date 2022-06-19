package com.example.buyauto_app.domain.util.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.execute(call: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch (Dispatchers.IO){
        call.invoke(this)
    }
}