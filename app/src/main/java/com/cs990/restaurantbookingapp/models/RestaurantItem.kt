package com.cs990.restaurantbookingapp.models

import android.os.Parcel
import android.os.Parcelable

/**
 * A Class that represents a specific Restaurant. Extends the Parcelable class.
 * @author Group 1
 * @version 1.0
 */
class RestaurantItem(name: String, restaurantImage: String, price: Long, rating: Long, geoHash: String ,
                     longitude: String, latitude: String, cuisine: String, dietaryFriendly: Boolean): Parcelable{

    /**
     * Constructor for this class.
     */
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

    /**
     * Returns the Name of this Restaurant
     */
    fun getName(): String {
        return this.name
    }

    /**
     * Returns the Latitude of this Restaurant
     */
    fun getLatitude(): String {
        return this.latitude
    }

    /**
     * Returns the Longitude of this Restaurant
     */
    fun getLongitude(): String {
        return this.longitude
    }

    /**
     * Returns the GeoHash of this Restaurant
     */
    fun getGeoHash(): String {
        return this.geoHash
    }

    /**
     * Returns the Price Category of this Restaurant
     */
    fun getPrice(): Long {
        return this.price
    }

    /**
     * Returns the Dietary Status of this Restaurant
     */
    fun getDietaryFriendly(): Boolean{
        return this.dietaryFriendly
    }

    /**
     * Returns the current User Rating of this Restaurant
     */
    fun getRating(): Long {
        return this.rating
    }

    /**
     * Returns the cover Image of this Restaurant
     */
    fun getRestaurantImage(): String {
        return this.restaurantImage
    }

    /**
     * Returns the type of Cuisine of this Restaurant
     */
    fun getCuisine(): String {
        return this.cuisine
    }

    /**
     * Sets the Name of this Restaurant
     */
    fun setName(name: String) {
        this.name = name
    }

    /**
     * Sets the Latitude of this Restaurant
     */
    fun setLatitude(latitude: String) {
        this.latitude = latitude
    }

    /**
     * Sets the Longitude of this Restaurant
     */
    fun setLongitude(longitude: String) {
        this.longitude = longitude
    }

    /**
     * Sets the GeoHash of this Restaurant
     */
    fun setGeoHash(geoHash: String) {
        this.geoHash = geoHash
    }

    /**
     * Sets the Price Category of this Restaurant
     */
    fun setPrice(price: Long) {
        this.price = price
    }

    /**
     * Sets the Dietary Status of this Restaurant
     */
    fun setDietaryFriendly(dietaryFriendly: Boolean){
        this.dietaryFriendly = dietaryFriendly
    }

    /**
     * Sets the User Rating of this Restaurant
     */
    fun setRating(rating: Long) {
        this.rating = rating
    }

    /**
     * Sets the cover Image of this Restaurant
     */
    fun setRestaurantImage(image: String) {
        this.restaurantImage = image
    }

    /**
     * Sets the type of Cuisine of this Restaurant
     */
    fun setCuisine(cuisine: String) {
        this.cuisine = cuisine
    }

    /**
     * Writes to and returns the current values of this Restaurant to a Parcel
     */
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

    /**
     * Contents description method inherited from Parcel
     */
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