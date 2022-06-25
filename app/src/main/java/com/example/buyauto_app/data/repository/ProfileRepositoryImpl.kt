package com.example.buyauto_app.data.repository

import com.example.buyauto_app.data.model.UserDto
import com.example.buyauto_app.data.repository.AddCarRepositoryImpl.Companion.CARS_COLL
import com.example.buyauto_app.data.repository.AuthRepositoryImpl.Companion.USER_COLL
import com.example.buyauto_app.domain.model.CarItem
import com.example.buyauto_app.domain.model.User
import com.example.buyauto_app.domain.repository.ProfileRepository
import com.example.buyauto_app.domain.util.Resource
import com.example.buyauto_app.domain.util.fetchData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import com.google.firebase.firestore.ktx.toObject


class ProfileRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ProfileRepository {

    private val carsCollection = firestore.collection(CARS_COLL)
    private val userCollection = firestore.collection(USER_COLL)

    override suspend fun getCurrentUserCarList(): Resource<List<CarItem>> {
        return withContext(Dispatchers.IO) {
            fetchData {
                val currentUid = auth.currentUser?.uid!!
                val data = carsCollection.whereEqualTo("ownerId", currentUid).get().await().toObjects(CarItem::class.java).onEach {
                    val user = getCurrentUser().data!!
                    it.ownerUserName = user.username
                }
                Resource.Success(data)
            }
        }
    }

    override suspend fun getCurrentUser(): Resource<User> {
        return withContext(Dispatchers.IO){
            fetchData {
                val currentUid = auth.currentUser?.uid!!
                val data  = userCollection.document(currentUid).get().await().toObject<UserDto>()
                Resource.Success(data?.toDomain()!!)
            }
        }
    }

    override suspend fun logOut(): Resource<Unit> {
        return withContext(Dispatchers.IO){
            fetchData {
                auth.signOut()
                Resource.Success(Unit)
            }
        }
    }
}