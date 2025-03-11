package com.example.gestiotech_v3.model.entities


data class Contract(
    var title: String = "",
    var description: String = "",
    var client: Client  = Client(),
    var list: List<Technician>,
    var id: String
)
