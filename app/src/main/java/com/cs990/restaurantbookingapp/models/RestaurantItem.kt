package com.cs990.restaurantbookingapp.models

import android.content.Context
import android.os.Build
import android.os.Parcel
import android.os.Parcelable

class RestaurantItem(name: String, restaurantImage: String, price: Long, rating: Long, geohash: String ,
                     longitude: String, latitude: String, cuisine: String, dietaryFriendly: Boolean){

    constructor(): this ("", "", 0, 0,
        "", "", "", "", false
    )


    private var name: String = name
    private var latitude: String = latitude
    private var longitude: String = longitude
    private var geohash: String = geohash
    private var price: Long = price
    private var dietaryFriendly: Boolean = dietaryFriendly
    private var rating: Long = rating
    private var cuisine: String = cuisine
    private var restaurantImage: String = restaurantImage



    fun getName(): String? {
        return this.name
    }
    fun getLatitude(): String? {
        return this.latitude
    }
    fun getLongitude(): String? {
        return this.longitude
    }
    fun getGeohash(): String? {
        return this.geohash
    }
    fun getPrice(): Long? {
        return this.price
    }
    fun getDietaryFriendly(): Boolean{
        return this.dietaryFriendly
    }
    fun getRating(): Long? {
        return this.rating
    }
    fun getRestaurantImage(): String? {
        return this.restaurantImage
    }
    fun getCuisine(): String? {
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
    fun setGeohash(geohash: String) {
        this.geohash = geohash
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
}