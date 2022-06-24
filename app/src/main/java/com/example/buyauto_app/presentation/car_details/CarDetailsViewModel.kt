package com.example.buyauto_app.presentation.car_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buyauto_app.domain.model.CarItem

class CarDetailsViewModel : ViewModel() {
    private val _carItem = MutableLiveData<CarItem>()
    val carItem: LiveData<CarItem> = _carItem

    fun setCarItem(carItem: CarItem) {
        _carItem.postValue(carItem)
    }
}