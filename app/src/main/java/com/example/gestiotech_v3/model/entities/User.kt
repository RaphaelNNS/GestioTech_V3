package com.example.gestiotech_v3.model.entities

import com.google.firebase.Timestamp

data class User(
    var name: String = "",
    var registerTime: Timestamp,
    var id: String = ""


){
    constructor() : this("", Timestamp.now(), "")
}


