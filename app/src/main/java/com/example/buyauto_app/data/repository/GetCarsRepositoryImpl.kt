package com.example.buyauto_app.data.repository

import com.example.buyauto_app.data.model.UserDto
import com.example.buyauto_app.data.repository.AuthRepositoryImpl.Companion.USER_COLL
import com.example.buyauto_app.domain.model.CarItem
import com.example.buyauto_app.domain.repository.GetCarRepository
import com.example.buyauto_app.domain.util.Resource
import com.example.buyauto_app.domain.util.fetchData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import com.google.firebase.firestore.ktx.toObject

class GetCarsRepositoryImpl(
    private val fireStore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : GetCarRepository {

    private val carsCollection = fireStore.collection(AddCarRepositoryImpl.CARS_COLL)
    private val userCollection = fireStore.collection(USER_COLL)
    override suspend fun getAllCarsItem(): Resource<List<CarItem>> {
        return withContext(Dispatchers.IO) {
            fetchData {
                val currentUid = auth.currentUser?.uid!!
                val data = carsCollection.whereNotEqualTo("ownerId", currentUid).get().await().toObjects(CarItem::class.java).onEach {
                    val user = getUserByUid(it.ownerId!!)
                    it.ownerUserName = user.data!!.username
                }
                Resource.Success(data)
            }
        }
    }

    override suspend fun getUserByUid(uid: String): Resource<UserDto> {
        return withContext(Dispatchers.IO){
            fetchData {
                val data = userCollection.document(uid).get().await().toObject<UserDto>()
                Resource.Success(data!!)
            }
        }
    }
}