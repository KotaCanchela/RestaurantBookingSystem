package com.cs990.restaurantbookingapp.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.PropertyName

class BookingItem(restaurant: RestaurantItem, guestNumber: String, day: String,
                  month: String, year: String, hour: String, minute: String): Parcelable {

    constructor(): this(RestaurantItem(), "", "", "", "", "","")

    private var restaurantItemName = restaurant.getName()
    private var restaurantItemImage = restaurant.getRestaurantImage()
    private var restaurantItemPrice = restaurant.getPrice()
    private var restaurantItemRating = restaurant.getRating()
    private var restaurantItemGeohash = restaurant.getGeoHash()
    private var restaurantItemLongitude = restaurant.getLongitude()
    private var restaurantItemLatitude = restaurant.getLatitude()
    private var restaurantItemCuisine = restaurant.getCuisine()
    private var restaurantItemDietary = restaurant.getDietaryFriendly()

    private var restaurantItem: RestaurantItem = RestaurantItem(
            restaurantItemName,
            restaurantItemImage,
            restaurantItemPrice,
            restaurantItemRating,
            restaurantItemGeohash,
            restaurantItemLongitude,
            restaurantItemLatitude,
            restaurantItemCuisine,
            restaurantItemDietary)

    private var guestNumber: String = guestNumber
    private var day: String = day
    private var month: String = month
    private var year: String = year
    private var hour: String = hour
    private var minute: String = minute

    constructor(parcel: Parcel) : this() {
        restaurantItemName = parcel.readString().toString()
        restaurantItemImage = parcel.readString().toString()
        restaurantItemPrice = parcel.readLong()
        restaurantItemRating = parcel.readLong()
        restaurantItemGeohash = parcel.readString().toString()
        restaurantItemLongitude = parcel.readString().toString()
        restaurantItemLatitude = parcel.readString().toString()
        restaurantItemCuisine = parcel.readString().toString()
        restaurantItemDietary = parcel.readByte() != 0.toByte()
        restaurantItem = parcel.readParcelable(RestaurantItem::class.java.classLoader)!!
        guestNumber = parcel.readString().toString()
        day = parcel.readString().toString()
        month = parcel.readString().toString()
        year = parcel.readString().toString()
        hour = parcel.readString().toString()
        minute = parcel.readString().toString()
    }


    fun getRestaurantItem(): RestaurantItem{
        this.restaurantItem.getName()
        this.restaurantItem.getRestaurantImage()
        this.restaurantItem.getPrice()
        this.restaurantItem.getRating()
        this.restaurantItem.getGeoHash()
        this.restaurantItem.getLongitude()
        this.restaurantItem.getLatitude()
        this.restaurantItem.getCuisine()
        this.restaurantItem.getDietaryFriendly()
        return this.restaurantItem
    }
    fun getGuestNumber(): String {
        return this.guestNumber
    }

    fun getDay(): String{
        return this.day
    }

    fun getMonth(): String{
        return this.month
    }

    fun getYear(): String{
        return this.year
    }

    fun getHour(): String{
        return this.hour
    }

    fun getMinute(): String{
        return this.minute
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(restaurantItemName)
        parcel.writeString(restaurantItemImage)
        parcel.writeLong(restaurantItemPrice)
        parcel.writeLong(restaurantItemRating)
        parcel.writeString(restaurantItemGeohash)
        parcel.writeString(restaurantItemLongitude)
        parcel.writeString(restaurantItemLatitude)
        parcel.writeString(restaurantItemCuisine)
        parcel.writeByte(if (restaurantItemDietary) 1 else 0)
        parcel.writeParcelable(restaurantItem, flags)
        parcel.writeString(guestNumber)
        parcel.writeString(day)
        parcel.writeString(month)
        parcel.writeString(year)
        parcel.writeString(hour)
        parcel.writeString(minute)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookingItem> {
        override fun createFromParcel(parcel: Parcel): BookingItem {
            return BookingItem(parcel)
        }

        override fun newArray(size: Int): Array<BookingItem?> {
            return arrayOfNulls(size)
        }
    }
}