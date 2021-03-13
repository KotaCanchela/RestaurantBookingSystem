package com.cs990.restaurantbookingapp.models

import android.content.Context
import android.os.Build
import android.os.Parcel
import android.os.Parcelable

class RestaurantItem(
    name: String = "",
    restaurantImage: String = "",
    price: Int = 0,
    rating: Int = 0,
    geohash: String = "",
    longitude: String = "",
    latitude: String = "",
    cuisine: String = "",
    dietaryFriendly: Int = 0,
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()!!,
        parcel.readInt()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()!!
    ) {
    }

    private val name: String = name
    private val latitude: String = latitude
    private val longitude: String = longitude
    private val geohash: String = geohash
    private val price: Int = price
    private val dietaryFriendly: Int = dietaryFriendly
    private val rating: Int = rating
    private val cuisine: String = cuisine
    private val restaurantImage: String? = restaurantImage


    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(name)
        writeString(latitude)
        writeString(longitude)
        writeString(geohash)
        writeInt(price)
        writeInt(dietaryFriendly)
        writeInt(rating)
        writeString(cuisine)
        writeString(restaurantImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RestaurantItem> {
        override fun createFromParcel(parcel: Parcel): RestaurantItem {
            return RestaurantItem(parcel)
        }

        override fun newArray(size: Int): Array<RestaurantItem?> {
            return arrayOfNulls(size)
        }
    }

    fun getRestaurantName(): String? {
        return this.name
    }
    fun getRestaurantLatitude(): String? {
        return this.latitude
    }
    fun getRestaurantLongitude(): String? {
        return this.longitude
    }
    fun getRestaurantGeohash(): String? {
        return this.geohash
    }
    fun getRestaurantPrice(): Int? {
        return this.price
    }
    fun getRestaurantDietary(): Boolean{
        return this.dietaryFriendly == 1
    }
    fun getRestaurantRating(): Int? {
        return this.rating
    }
    fun getRestaurantImage(): String? {
        return this.restaurantImage
    }
    fun getRestaurantCuisine(): String? {
        return this.cuisine
    }
}