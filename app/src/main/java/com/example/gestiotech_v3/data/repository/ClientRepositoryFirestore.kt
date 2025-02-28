package com.example.gestiotech_v3.data.repository

import com.example.gestiotech_v3.model.entities.Client
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await

class ClientRepositoryFirestore: IClientRepository {

    override suspend fun getClients(): List<Client> {
        val dataBase = FirebaseFirestore.getInstance()

        val clients = ArrayList<Client>(

        )
        val result = dataBase.collection("Clients").get().await()

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

        val mapClient = mapOf(
            "name" to client.name,
            "documentNumber" to client.documentNumber,
            "phoneNumber" to client.phoneNumber,
            "description" to client.description,
            "address" to client.address


        )

        dataBase
            .collection("Clients")
            .document(client.id)
            .set(mapClient, SetOptions.merge())
            .await()
    }

    /**
     * The passed client ID is redundant; an ID will be created automatically.
     * */
    override suspend fun addClient(client: Client): Client{
        val dataBase = FirebaseFirestore.getInstance()
        val mapClient = mapOf(
            "name" to client.name,
            "documentNumber" to client.documentNumber,
            "phoneNumber" to client.phoneNumber,
            "description" to client.description,
            "address" to client.address


        )
        val document =
            dataBase
            .collection("Clients")
            .add(mapClient)
            .await()

        return client.copy(id = document.id)
    }

}