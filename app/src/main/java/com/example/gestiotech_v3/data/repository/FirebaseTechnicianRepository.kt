package com.example.gestiotech_v3.data.repository

import com.example.gestiotech_v3.model.entities.Technician
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseTechnicianRepository: ITechnicianRepository {
    override suspend fun getTechnicians(): List<Technician> {
        val database = FirebaseFirestore.getInstance()
        val result = database.collection("Technicians").get().await()
        val tecList = ArrayList<Technician>()

        result.documents.mapNotNull {
            val tec = it.toObject(Technician::class.java)
            tec?.id = it.id
            if (tec != null) {
                tecList.add(tec)
            }
        }

        return tecList
    }

    override suspend fun addTechnician(tec: Technician): Technician {
        val database = FirebaseFirestore.getInstance()
        val result = database.collection("Technicians").add(tec).await()
        tec.id = result.id
        return tec
    }

    override suspend fun deleteTechnician(id: String) {
        val database = FirebaseFirestore.getInstance()
        database.collection("Technicians").document(id).delete()
    }


}
