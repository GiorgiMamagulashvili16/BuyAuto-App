package com.example.buyauto_app.domain.repository

import android.net.Uri
import com.example.buyauto_app.domain.model.CarItem
import com.example.buyauto_app.domain.util.Resource

interface AddCarRepository {

    suspend fun addCarItem(carItem: CarItem,imagesUriItems:List<Uri>): Resource<Unit>
}