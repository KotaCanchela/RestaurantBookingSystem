package com.cs990.restaurantbookingapp.models

import android.os.Parcel
import android.os.Parcelable

/**
 * A Class that represents a registered User of the Application. Accepts a Parcel of values and
 * Extends the Parcelable class.
 * @author Group 1
 * @version 1.0
 */
class User (
    val id: String = "",
    val password: String = "",
    val name: String = "",
    val image: String = "",
    val mobile: String = "",
    val fcmToken: String = ""): Parcelable {

    /**
     * Constructor for this class. Instantiate's values from the Parcel parameter
     */
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    /**
     * Writes to and returns the current values of this User to a Parcel
     */
    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(id)
        writeString(password)
        writeString(name)
        writeString(image)
        writeString(mobile)
        writeString(fcmToken)
    }

    /**
     * Contents description method inherited from Parcel
     */
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