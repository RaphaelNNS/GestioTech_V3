package com.example.gestiotech_v3.model.entities

data class Technician(
    var name: String = "",
    var description: String = "",
    var email: String = "",
    var phoneNumber: String = "",
    var documentNumber: String = "",
    var contractsId: List<Int> = emptyList(),
    var ownerId: String = "",
    var id: String = ""
) {
    constructor(): this ("","","","","", emptyList(),"")
}
