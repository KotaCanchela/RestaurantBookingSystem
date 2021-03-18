package com.cs990.restaurantbookingapp.models

class BookingItem(restaurantItem: RestaurantItem, guestNumber: String, day: String,
                  month: String, year: String, hour: String, minute: String) {

    constructor(): this(RestaurantItem("", "", 0, 0,
            "", "", "", "", false), "", "", "", "", "","")

    private var restaurantItemName = restaurantItem.getName()
    private var restaurantItemImage = restaurantItem.getRestaurantImage()
    private var restaurantItemPrice = restaurantItem.getPrice()
    private var restaurantItemRating = restaurantItem.getRating()
    private var restaurantItemGeohash = restaurantItem.getGeoHash()
    private var restaurantItemLongitude = restaurantItem.getLongitude()
    private var restaurantItemLatitude = restaurantItem.getLatitude()
    private var restaurantItemCuisine = restaurantItem.getCuisine()
    private var restaurantItemDietary = restaurantItem.getDietaryFriendly()
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
}