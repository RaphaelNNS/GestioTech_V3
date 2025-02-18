package com.example.gestiotech_v3.model.auth

import com.example.gestiotech_v3.model.entities.Client
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
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

    suspend fun getClients(): List<Client> {
        val dataBase = FirebaseFirestore.getInstance()
        try {
            val clients = ArrayList<Client>()
            val result = dataBase.collection("Clients").get().await()

            result.documents.mapNotNull { document ->
                val client = document.toObject(Client::class.java)
                client?.let { clients.add(it) } // Adiciona o client se não for nulo
            }

            return clients
        }catch (e: Exception){
            throw e
        }
    }

    suspend fun addUser(name: String, password: String, documentNumber: String): String {
        val dataBase = FirebaseFirestore.getInstance()
        val cliente = mapOf(
            "id" to "4",
            "name" to name,
            "password" to password,
            "documentNumber" to documentNumber
        )
        try {
            dataBase
                .collection("Clients")
                .add(cliente)
                .await()

            return cliente.toString()
        }catch (e:Exception){
            throw e
        }
    }

    fun logOut(){
        val auth = FirebaseAuth.getInstance()
        auth.signOut()
    }

}