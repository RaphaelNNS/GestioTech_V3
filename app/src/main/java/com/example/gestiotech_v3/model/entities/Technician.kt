package com.example.gestiotech_v3.model.entities

data class Technician(
    var name: String = "",
    var description: String = "",
    var email: String = "",
    var password: String = "",
    var phoneNumber: String = "",
    var documentNumber: String = "",
    var id: String = ""
) {
    constructor(): this ("","","","","","","",)
}
