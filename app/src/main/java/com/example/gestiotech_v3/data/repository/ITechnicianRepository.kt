package com.example.gestiotech_v3.data.repository

import com.example.gestiotech_v3.model.entities.Technician

interface ITechnicianRepository {
    suspend fun getTechnicians(): List<Technician>
    suspend fun getTechnician(id: String): Technician?
    suspend fun addTechnician(tec: Technician): Technician
    fun deleteTechnician(id: String)
    suspend fun editTechnician(tec: Technician)


}
