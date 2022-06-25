package com.example.buyauto_app.presentation.car_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buyauto_app.data.model.NotificationData
import com.example.buyauto_app.domain.model.CarItem
import com.example.buyauto_app.domain.repository.NotificationRepository
import com.example.buyauto_app.domain.util.extensions.execute
import com.google.firebase.auth.FirebaseAuth

class CarDetailsViewModel(
    private val notificationRepository: NotificationRepository
) : ViewModel() {
    private val _carItem = MutableLiveData<CarItem>()
    val carItem: LiveData<CarItem> = _carItem

    fun setCarItem(carItem: CarItem) {
        _carItem.postValue(carItem)
    }

    fun sendNotification(receiverId: String,senderUsername:String,carModel:String) = execute {
        notificationRepository.sendNotification(
            NotificationData(
                FirebaseAuth.getInstance().currentUser?.uid!!,
                "Order",
                "$senderUsername is interested in your $carModel"
            ),
            receiverId
        )
    }
}