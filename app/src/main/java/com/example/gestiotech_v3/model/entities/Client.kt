package com.example.gestiotech_v3.model.entities

data class Client(
    val id: String = "",
    val name: String = "",
    val documentNumber: String = "",
    val password: String = ""
){
    constructor() : this("", "", "", "")
}