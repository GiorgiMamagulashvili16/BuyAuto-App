package com.example.buyauto_app.presentation.cars_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buyauto_app.domain.usecase.get_cars.GetCarsUseCase
import com.example.buyauto_app.domain.util.extensions.execute

class CarsViewModel(
    private val getCarsUseCase: GetCarsUseCase
) : ViewModel() {

    private val _screenState = MutableLiveData<CarsScreenState>()
    val screenState: LiveData<CarsScreenState> = _screenState

    fun getAllCarsData() = execute {
        _screenState.postValue(CarsScreenState(isLoading = true))
        _screenState.postValue(getCarsUseCase.getAllCars())
    }
}