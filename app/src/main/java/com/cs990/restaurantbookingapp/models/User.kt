package com.cs990.restaurantbookingapp.models

import android.os.Parcel
import android.os.Parcelable

class User (
    val id: String = "",
    val password: String = "",
    val name: String = "",
    val image: String = "",
    val mobile: String = "",
    val fcmToken: String = ""): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(id)
        writeString(password)
        writeString(name)
        writeString(image)
        writeString(mobile)
        writeString(fcmToken)

    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}