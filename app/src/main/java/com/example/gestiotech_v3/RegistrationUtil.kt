package com.example.gestiotech_v3

import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

object RegistrationUtil {

    private val  existingUsers = listOf("Peter", "Carl")

    fun validateRegistration(
        email: String,
        password: String,
        confirmedPassword: String
    ): Boolean {
        return true
    }

    suspend fun deleteAllUsers() {
        val db = FirebaseFirestore.getInstance()
        val usersCollection = db.collection("Clients")

        try {
            // Obtém todos os documentos da coleção "Users"
            val snapshot = usersCollection.get().await()

            // Deleta cada documento individualmente
            for (document in snapshot.documents) {
                usersCollection.document(document.id).delete().await()
                println("Usuário ${document.id} excluído com sucesso.")
            }

            println("Todos os usuários foram excluídos!")
        } catch (e: Exception) {
            println("Erro ao excluir usuários: ${e.message}")
        }
    }
}