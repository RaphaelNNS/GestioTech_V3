package com.example.gestiotech_v3.model.entities

data class Client(
    val id: String = "",
    val name: String = "",
    val documentNumber: String = "",
    val adress: String = "",
    val phoneNumber: String = "",
    val description: String = ""
){
    constructor() : this("", "", "", "", "", "")
}