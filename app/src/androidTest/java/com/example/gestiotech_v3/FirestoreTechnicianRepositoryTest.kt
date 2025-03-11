package com.example.gestiotech_v3

import com.example.gestiotech_v3.data.repository.FirebaseTechnicianRepository
import com.example.gestiotech_v3.data.repository.ITechnicianRepository
import com.example.gestiotech_v3.model.entities.Technician
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FirestoreTechnicianRepositoryTest {

    private lateinit var repository: ITechnicianRepository

    @Before
    fun setup(){
        repository = FirebaseTechnicianRepository()
    }

    @Test
    fun getTechnicians() = runTest{
        val tecList = repository.getTechnicians()
        assertThat(tecList).isNotNull()
    }

    @Test
    fun addTechnician() = runTest{
        val tec = Technician(
            name = "#Name",
            description = "#Description",
            email = "#Email",
            phoneNumber = "#PhoneNumber",
            documentNumber = "#DocumentNumber",
            contractsId = emptyList<Int>(),
            ownerId = "sd"
        )

        val newTec = repository.addTechnician(tec)
        val tecList = repository.getTechnicians()

        assertThat(tecList).contains(newTec)

        //Todo(delete tec )
    }

    @Test
    fun deleteTechnicians() = runTest {

        val tec = Technician(
            name = "#Name",
            description = "#Description",
            email = "#Email",
            phoneNumber = "#PhoneNumber",
            documentNumber = "#DocumentNumber",
            contractsId = emptyList<Int>(),
            ownerId = "sd"
        )

        val newTec = repository.addTechnician(tec)
        repository.deleteTechnician(newTec.id)
        val tecList = repository.getTechnicians()

        assertThat(tecList).doesNotContain(newTec)

    }


}

