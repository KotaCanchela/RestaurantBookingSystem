package com.cs990.restaurantbookingapp

import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity

class RestaurantDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_details)
        val ratingBar = findViewById<View>(R.id.ratingBar) as RatingBar
        ratingBar.rating = "3.6".toFloat()
    }
}