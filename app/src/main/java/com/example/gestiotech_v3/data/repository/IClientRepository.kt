package com.example.gestiotech_v3.data.repository

import com.example.gestiotech_v3.model.entities.Client

interface IClientRepository {
    abstract suspend fun addClient(client: Client): Client

}
