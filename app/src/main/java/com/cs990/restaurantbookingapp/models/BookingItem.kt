package com.cs990.restaurantbookingapp.models

class BookingItem(restaurantItem: RestaurantItem, guestNumber: String, day: String, month: String, year: String, hour: String, minute: String) {

    private var restaurantItem: RestaurantItem = restaurantItem
    private var guestNumber: String = guestNumber
    private var day: String = day
    private var month: String = month
    private var year: String = year
    private var hour: String = hour
    private var minute: String = minute



    fun getRestaurant(): RestaurantItem{
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