package com.example.buyauto_app.domain.model

import com.google.firebase.firestore.Exclude
import java.io.Serializable

data class CarItem(
    val ownerId: String? = null,
    val orderId: String? = null,
    val manufacture: String? = null,
    val year: String? = null,
    val model: String? = null,
    val description: String? = null,
    val carImageList: List<String>? = null,
    val date: Long? = null,
    val lat: Double? = null,
    val long: Double? = null,
    val price: Int? = null,
    @get:Exclude var ownerUserName: String? = null,
    val ownerNumber: String? = null
) : Serializable
