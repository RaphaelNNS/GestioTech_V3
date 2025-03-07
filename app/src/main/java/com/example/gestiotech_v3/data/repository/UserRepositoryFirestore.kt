package com.example.gestiotech_v3.data.repository

import com.example.gestiotech_v3.model.entities.User
import com.google.firebase.Timestamp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepositoryFirestore : IUserRepository {

    override suspend fun loginEmailPassword(email: String, senha: String): AuthResult {
        require(email.isNotEmpty() && senha.isNotEmpty()) { "E-mail e senha não podem estar vazios" }
        val auth = FirebaseAuth.getInstance()
        return auth.signInWithEmailAndPassword(email, senha).await()
    }

    override suspend fun registerEmailPassword(email: String, senha: String, name: String): User {
        require(email.isNotEmpty() && senha.isNotEmpty()) { "E-mail e senha não podem estar vazios" }
        val auth = FirebaseAuth.getInstance()
        val authResult = auth.createUserWithEmailAndPassword(email, senha).await()
        val userId = authResult.user?.uid ?: throw Exception("Falha ao obter o UID do usuário")
        return addUser(User(name, Timestamp.now(), userId))
    }

    override fun logOut() {
        FirebaseAuth.getInstance().signOut()
    }

    override suspend fun deleteUser(id: String): Boolean {
        // Deletes user infos from "Users" collection
        val database = FirebaseFirestore.getInstance()
        database.collection("Users").document(id).delete().await()
        // Deletes user
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.currentUser?.delete()?.await()
        return true
    }

    override suspend fun getUsers(): List<User> {
        val database = FirebaseFirestore.getInstance()
        val result = database.collection("Users").get().await()
        return result.documents.mapNotNull { it.toObject(User::class.java) }
    }

    suspend fun addUser(user: User): User {
        val userMap = mapOf(
            "name" to user.name,
            "id" to user.id,
            "registerTime" to user.registerTime
        )
        val database = FirebaseFirestore.getInstance()
        database.collection("Users").document(user.id).set(userMap).await()
        return user
    }
}
