package com.example.buyauto_app.data.repository

import com.example.buyauto_app.domain.model.CarItem
import com.example.buyauto_app.domain.repository.GetCarRepository
import com.example.buyauto_app.domain.util.Resource
import com.example.buyauto_app.domain.util.fetchData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class GetCarsRepositoryImpl(
    private val fireStore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : GetCarRepository {

    private val carsCollection = fireStore.collection(AddCarRepositoryImpl.CARS_COLL)

    override suspend fun getAllCarsItem(): Resource<List<CarItem>> {
        return withContext(Dispatchers.IO) {
            fetchData {
                val currentUid = auth.currentUser?.uid!!
                val data = carsCollection.whereNotEqualTo("ownerId", currentUid).get().await().toObjects(CarItem::class.java)
                Resource.Success(data)
            }
        }
    }
}