package com.example.gestiotech_v3.model.entities

import android.os.Parcel
import android.os.Parcelable

data class Client(
    var id: String = "",
    var name: String = "",
    var documentNumber: String = "",
    var address: String = "",
    var phoneNumber: String = "",
    var description: String = ""
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
    ) {
    }

    constructor() : this("", "", "", "", "", "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(documentNumber)
        parcel.writeString(address)
        parcel.writeString(phoneNumber)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Client> {
        override fun createFromParcel(parcel: Parcel): Client {
            return Client(parcel)
        }

        override fun newArray(size: Int): Array<Client?> {
            return arrayOfNulls(size)
        }
    }
}