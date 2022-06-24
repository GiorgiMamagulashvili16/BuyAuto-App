package com.example.buyauto_app.presentation.add_car_screen

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buyauto_app.domain.model.CarItem
import com.example.buyauto_app.domain.usecase.add_car.AddCarUseCase
import com.example.buyauto_app.domain.usecase.add_car.AddCarValidatorUseCase
import com.example.buyauto_app.domain.util.extensions.execute

class AddCarViewModel(
    private val addCarValidatorUseCase: AddCarValidatorUseCase,
    private val addCarUseCase: AddCarUseCase
) : ViewModel() {

    private val _selectedImages = MutableLiveData<List<Uri>>()
    val selectedImages: LiveData<List<Uri>> = _selectedImages

    private val _screenState = MutableLiveData<AddCarScreenState>()
    val screenState: LiveData<AddCarScreenState> = _screenState

    fun setImages(images: List<Uri>) {
        _selectedImages.postValue(images)
    }

    fun addCarItem(
        lat: Double,
        long: Double,
        manufacture: String,
        model: String,
        year: String,
        description: String,
        price: Int,
        number: String
    ) {
        execute {
            _screenState.postValue(AddCarScreenState(isLoading = true))
            val carItem = CarItem(
                lat = lat,
                long = long,
                manufacture = manufacture,
                model = model,
                year = year,
                description = description,
                price = price,
                ownerNumber = number
            )
            addCarValidatorUseCase.validate(
                lat,
                long,
                manufacture,
                model,
                year,
                description,
                selectedImages.value!!,
                price,
                number
            ).also {
                if (it.success) {
                    _screenState.postValue(addCarUseCase.addCar(carItem, selectedImages.value!!))
                } else {
                    _screenState.postValue(AddCarScreenState(errorMessage = it.errorMess))
                }
            }
        }
    }
}