package com.example.gestiotech_v3.data.repository

import com.example.gestiotech_v3.model.entities.Client
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ClientRepositoryFirestore: IClientRepository {

    override suspend fun getClients(): List<Client> {
        val dataBase = FirebaseFirestore.getInstance()

        val clients = ArrayList<Client>()
        val result = dataBase.collection("Clients").get().await()

        result.documents.mapNotNull { document ->
            var client = document.toObject(Client::class.java)
            client?.let {
                client.id = document.id
                clients.add(it)
            } // Adiciona o client se não for nulo
        }

        return clients
    }

    override suspend fun editCLients(client: Client, id: String) {
        val dataBase = FirebaseFirestore.getInstance()

        dataBase
            .collection("Clients")
            .document(id)
            .set(client)
            .await()
    }

    override suspend fun addClient(client: Client): Client{
        val dataBase = FirebaseFirestore.getInstance()
        val mapClient = mapOf(
            "name" to client.name,
            "documentNumber" to client.documentNumber,
            "phoneNumber" to client.phoneNumber,
            "description" to client.description,
            "adress" to client.adress


        )
        dataBase
            .collection("Clients")
            .add(mapClient)
            .await()

        return client
    }

}