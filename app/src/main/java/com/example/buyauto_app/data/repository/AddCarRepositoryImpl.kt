package com.example.buyauto_app.data.repository

import android.net.Uri
import com.example.buyauto_app.domain.model.CarItem
import com.example.buyauto_app.domain.repository.AddCarRepository
import com.example.buyauto_app.domain.util.Resource
import com.example.buyauto_app.domain.util.fetchData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*

class AddCarRepositoryImpl(
    firestore: FirebaseFirestore,
    private val storage: FirebaseStorage,
    private val auth: FirebaseAuth
) : AddCarRepository {
    private val carsCollection = firestore.collection(CARS_COLL)
    override suspend fun addCarItem(carItem: CarItem, imagesUriItems: List<Uri>): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            fetchData {
                with(carItem) {
                    val uid = auth.currentUser?.uid!!
                    val carId = UUID.randomUUID().toString()
                    val imageUrls = mutableListOf<String>()
                    imagesUriItems.forEach {
                        val imageUpl = storage.getReference(carId).putFile(it).await()
                        val imageUrl = imageUpl.metadata?.reference?.downloadUrl?.await().toString()
                        imageUrls.add(imageUrl)
                    }
                    val carUploadItem = mutableMapOf(
                        "ownerId" to uid,
                        "date" to System.currentTimeMillis(),
                        "lat" to lat as Number,
                        "long" to long as Number,
                        "carId" to carId,
                        "carImageList" to imageUrls,
                        "manufacture" to manufacture,
                        "model" to model,
                        "year" to year,
                        "description" to description,
                    )
                    carsCollection.document(carId).set(carUploadItem).await()
                    Resource.Success(Unit)
                }
            }
        }
    }

    companion object {
        private const val CARS_COLL = "cars_collection"
    }
}