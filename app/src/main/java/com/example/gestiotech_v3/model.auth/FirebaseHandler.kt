package com.example.gestiotech_v3.model.auth

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseHandler {

    suspend fun loginEmailPassword(email: String, senha: String): AuthResult? {
        if ((email.isEmpty() || senha.isEmpty())) {
            throw Exception("E-mail e senha não podem estar vazios")
        }
        try {
            val auth = FirebaseAuth.getInstance()
            val authResult = auth.signInWithEmailAndPassword(
                email, senha
            ).await()
            return authResult
        }catch (e : Exception){
            throw e
        }
    }

    suspend fun registerEmailPassword(email: String, senha: String): AuthResult? {
        if ((email.isEmpty() || senha.isEmpty())) {
            throw Exception("E-mail e senha não podem estar vazios")
        }
        try {
            val auth = FirebaseAuth.getInstance()
            val authResult = auth.createUserWithEmailAndPassword(
                email, senha
            ).await()
            return authResult
        }catch (e : Exception){
            throw e
        }
    }

    fun logOut(){
        val auth = FirebaseAuth.getInstance()
        auth.signOut()
    }

}