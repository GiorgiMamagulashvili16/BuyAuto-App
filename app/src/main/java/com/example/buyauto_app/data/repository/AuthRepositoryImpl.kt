package com.example.buyauto_app.data.repository

import com.example.buyauto_app.data.data.UserDto
import com.example.buyauto_app.domain.model.User
import com.example.buyauto_app.domain.repository.AuthRepository
import com.example.buyauto_app.domain.util.Resource
import com.example.buyauto_app.domain.util.fetchData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {
    private val userCollection = firestore.collection(USER_COLL)
    override suspend fun singIn(email: String, password: String): Resource<AuthResult>  = withContext(Dispatchers.IO) {
            return@withContext fetchData {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                Resource.Success(result)
            }
        }

    override suspend fun signUp(user: User): Resource<AuthResult> = withContext(Dispatchers.IO) {
        return@withContext  fetchData {
            with(user){
                val auth = auth.createUserWithEmailAndPassword(email,password!!).await()
                val uid = auth.user?.uid!!
                val resultUser = UserDto(
                    uid = uid,
                    username = username,
                    email = email
                )
                userCollection.document(uid).set(resultUser).await()
                Resource.Success(auth)
            }
        }
    }
    companion object {
        private const val USER_COLL = "user_collection"
    }
}