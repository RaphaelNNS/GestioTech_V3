package com.example.gestiotech_v3.data.repository

import com.example.gestiotech_v3.model.entities.Technician

interface ITechnicianRepository {
    suspend fun getTechnicians(): List<Technician>
    suspend fun addTechnician(tec: Technician): Technician
    suspend fun deleteTechnician(id: String)


}
