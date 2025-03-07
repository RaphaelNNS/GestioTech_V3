package com.example.gestiotech_v3.data.repository

import com.google.firebase.auth.AuthResult
import com.example.gestiotech_v3.model.entities.User

interface IUserRepository {
    abstract suspend fun loginEmailPassword(email: String, senha: String): AuthResult?

    abstract suspend fun registerEmailPassword(email: String, senha: String, name: String): User

    abstract suspend fun getUsers(): List<User>

    abstract fun logOut()

    abstract suspend fun deleteUser(id: String): Boolean
}
