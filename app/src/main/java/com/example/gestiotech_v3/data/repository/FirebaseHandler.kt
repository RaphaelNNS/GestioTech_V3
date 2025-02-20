package com.example.gestiotech_v3.model.repository

import com.example.gestiotech_v3.model.entities.Client
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
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

    suspend fun addClient(client: Client): String {
        val dataBase = FirebaseFirestore.getInstance()
        val cliente = mapOf(
            "name" to client.name,
            "documentNumber" to client.documentNumber,
            "phoneNumber" to client.phoneNumber,
            "description" to client.description,
            "adress" to client.adress


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