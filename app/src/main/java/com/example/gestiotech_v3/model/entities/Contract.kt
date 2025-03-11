package com.example.gestiotech_v3.model.entities


data class Contract(
    var title: String = "",
    var description: String = "",
    var tecIdList: List<Int>,
    var clientIdList: List<Int>,
    var ownerId: String = "",
    var id: String
)
