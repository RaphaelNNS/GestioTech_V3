package com.example.gestiotech_v3

import com.example.gestiotech_v3.data.repository.ClientRepositoryFirestore
import com.example.gestiotech_v3.data.repository.UserRepositoryFirestore
import com.example.gestiotech_v3.model.entities.User
import com.google.common.truth.Truth.assertThat
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FirestoreUserRepositoryTest {

    private lateinit var repository: UserRepositoryFirestore

    @Before
    fun setup() {
        repository = UserRepositoryFirestore()
    }

    @Test
    fun when_register_an_user_infos_should_be_saved_on_users_collection() = runTest {
        val user = User(
            name = "#Name#",
            registerTime = Timestamp.now()
        )
        val email = "testemail@gmail.com"
        val senha = "senhasenhasenha"

        val newUser = repository.registerEmailPassword(email, senha, user.name)

        val userList = repository.getUsers()

        assertThat(userList).contains(newUser)

    }


    @Test
    fun when_login_currentUser_shouldnt_return_null() = runTest {
        val email = "testemail@gmail.com"
        val senha = "senhasenhasenha"

        val result = repository.loginEmailPassword(email,senha)

        delay(1000)

        assertThat(result).isNotNull()

        result.user?.uid?.let { repository.deleteUser(it) }


    }
}


