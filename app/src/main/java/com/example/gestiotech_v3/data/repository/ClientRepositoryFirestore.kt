package com.example.gestiotech_v3.data.repository

import com.example.gestiotech_v3.model.entities.Client
import com.example.gestiotech_v3.model.entities.Technician
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ClientRepositoryFirestore @Inject constructor(): IClientRepository {

    override suspend fun getClients(): List<Client> {
        val firebaseAuth = FirebaseAuth.getInstance()
        val dataBase = FirebaseFirestore.getInstance()

        val clients = ArrayList<Client>()
        val result = dataBase.collection("Clients").whereEqualTo("ownerId", firebaseAuth.uid.toString()).get().await()

        result.documents.mapNotNull { document ->
            var client = document.toObject(Client::class.java)
            client?.let {
                client.id = document.id
                clients.add(it)
            }
        }

        return clients
    }

    override suspend fun editCLients(client: Client) {
        val dataBase = FirebaseFirestore.getInstance()

        val mapClient = client.copy()

        dataBase
            .collection("Clients")
            .document(client.id)
            .set(mapClient, SetOptions.merge())
            .await()
    }

    override fun deleteCLients(id: String) {
        val dataBase = FirebaseFirestore.getInstance()

        dataBase.collection("Clients").document(id).delete()
    }

    override suspend fun getClient(id: String): Client? {
        val database = FirebaseFirestore.getInstance()
        val result = database.collection("Clients").document(id).get().await()
        val client = result.toObject(Client::class.java)
        client?.id = result.id
        return client

    }

    /**
     * The provided client ID is redundant; an ID will be automatically generated.
     * */
    override suspend fun addClient(client: Client): Client{
        val database = FirebaseFirestore.getInstance()
        val firebase = FirebaseAuth.getInstance()
        val ownerId = firebase.currentUser?.uid ?: ""

        val newClient = client.copy(ownerId = ownerId) // Cria uma cópia imutável do objeto
        val result = database.collection("Clients").add(newClient).await()
        return newClient.copy(id = result.id)
    }

}