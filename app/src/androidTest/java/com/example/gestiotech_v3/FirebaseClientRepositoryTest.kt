package com.example.gestiotech_v3

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.gestiotech_v3.data.repository.ClientRepositoryFirestore
import com.example.gestiotech_v3.model.entities.Client
import com.google.common.truth.Truth.assertThat
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.firestoreSettings
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FirebaseClientRepositoryTest {

    private lateinit var database: FirebaseFirestore
    private lateinit var repository: ClientRepositoryFirestore

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = FirebaseFirestore.getInstance()
        repository = ClientRepositoryFirestore()
    }


    @Test
    fun insertClient() = runTest {

        val client = repository.addClient(Client("", "#Nome#",
            "#DocumentNumber#", "#Adress#",
            "#PhoneNumber#", "#Description#"))

        val clients = repository.getClients()


        assertThat(clients).contains(client)
    }

    @Test
    fun editClient() = runTest {

        val client = repository.addClient(Client("", "#Name#",
            "#DocumentNumber#", "#Adress#",
            "#PhoneNumber#", "#Description#"))

        client.name = "#NewName#"
        repository.editCLients(client)

        delay(2000)
        val clients = repository.getClients()
        assertThat(clients).contains(client)
    }



}