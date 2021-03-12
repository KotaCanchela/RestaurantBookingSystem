package com.cs990.restaurantbookingapp

import android.content.Context
import java.util.*

class RestaurantItems(name: String, distance: String, rating: Int, restaurantImage: Int) {

    private val name: String = name
    private val distance: String = distance
    private val rating: Int = rating
    private val restaurantImage: Int = restaurantImage

    fun getRestaurantName(): String{
        return this.name
    }
    fun getRestaurantDistance(): String{
        return this.distance
    }
    fun getRestaurantRating(): Int{
        return this.rating
    }
    fun getRestaurantImage(): Int{
        return this.restaurantImage
    }


}