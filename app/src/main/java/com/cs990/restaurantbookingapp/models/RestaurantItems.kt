package com.cs990.restaurantbookingapp.models

import android.content.Context
import java.util.*

class RestaurantItems(name: String, rating: Int, restaurantImage: Int) {

    private val name: String = name
    private val rating: Int = rating
    private val restaurantImage: Int = restaurantImage

    fun getRestaurantName(): String{
        return this.name
    }

    fun getRestaurantRating(): Int{
        return this.rating
    }
    fun getRestaurantImage(): Int{
        return this.restaurantImage
    }


}