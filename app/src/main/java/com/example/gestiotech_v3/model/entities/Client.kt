package com.example.gestiotech_v3.model.entities


data class Client(
    var id: String = "",
    var name: String = "",
    var documentNumber: String = "",
    var address: String = "",
    var phoneNumber: String = "",
    var description: String = "",
    var contractsId: List<Contract> = emptyList()
)
