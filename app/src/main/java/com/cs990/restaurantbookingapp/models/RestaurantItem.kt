package com.cs990.restaurantbookingapp.models

import android.os.Parcel
import android.os.Parcelable

class RestaurantItem(name: String, restaurantImage: String, price: Long, rating: Long, geoHash: String ,
                     longitude: String, latitude: String, cuisine: String, dietaryFriendly: Boolean): Parcelable{

    constructor(): this ("", "", 0, 0,
        "", "", "", "", false
    )


    private var name: String = name
    private var restaurantImage: String = restaurantImage
    private var price: Long = price
    private var rating: Long = rating
    private var geoHash: String = geoHash
    private var longitude: String = longitude
    private var latitude: String = latitude
    private var cuisine: String = cuisine
    private var dietaryFriendly: Boolean = dietaryFriendly

    private constructor(parcel: Parcel) : this() {
        name = parcel.readString().toString()
        restaurantImage = parcel.readString().toString()
        price = parcel.readLong()
        rating = parcel.readLong()
        geoHash = parcel.readString().toString()
        longitude = parcel.readString().toString()
        latitude = parcel.readString().toString()
        cuisine = parcel.readString().toString()
        dietaryFriendly = parcel.readByte() != 0.toByte()
    }


    fun getName(): String {
        return this.name
    }
    fun getLatitude(): String {
        return this.latitude
    }
    fun getLongitude(): String {
        return this.longitude
    }
    fun getGeoHash(): String {
        return this.geoHash
    }
    fun getPrice(): Long {
        return this.price
    }
    fun getDietaryFriendly(): Boolean{
        return this.dietaryFriendly
    }
    fun getRating(): Long {
        return this.rating
    }
    fun getRestaurantImage(): String {
        return this.restaurantImage
    }
    fun getCuisine(): String {
        return this.cuisine
    }

    // are you actually calling this somewhere?
    fun setName(name: String) {
        this.name = name
    }
    fun setLatitude(latitude: String) {
        this.latitude = latitude
    }
    fun setLongitude(longitude: String) {
        this.longitude = longitude
    }
    fun setGeoHash(geoHash: String) {
        this.geoHash = geoHash
    }
    fun setPrice(price: Long) {
        this.price = price
    }
    fun setDietaryFriendly(dietaryFriendly: Boolean){
        this.dietaryFriendly = dietaryFriendly
    }
    fun setRating(rating: Long) {
        this.rating = rating
    }
    fun setRestaurantImage(image: String) {
        this.restaurantImage = image
    }
    fun setCuisine(cuisine: String) {
        this.cuisine = cuisine
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(restaurantImage)
        parcel.writeLong(price)
        parcel.writeLong(rating)
        parcel.writeString(geoHash)
        parcel.writeString(longitude)
        parcel.writeString(latitude)
        parcel.writeString(cuisine)
        parcel.writeByte(if (dietaryFriendly) 1 else 0)
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
}