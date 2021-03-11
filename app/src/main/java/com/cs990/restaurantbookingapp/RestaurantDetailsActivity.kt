package com.cs990.restaurantbookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView

class RestaurantDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_details)
        val textView0 = findViewById<View>(R.id.ResName) as TextView
        textView0.text = "Restaurant Name"
        val textView1 = findViewById<View>(R.id.address1) as TextView
        textView1.text = "12/28"
        val textView2 = findViewById<View>(R.id.address2) as TextView
        textView2.text = "Livingstone Tower"
        val textView3 = findViewById<View>(R.id.address3) as TextView
        textView3.text = "G1 G2G"
        val textView4 = findViewById<View>(R.id.reviewCount) as TextView
        textView4.text = "1,024 reviews"
        val textView5 = findViewById<View>(R.id.telNo) as TextView
        textView5.text = "tel: 12345678910"
        val rate = findViewById<View>(R.id.ratingBar) as RatingBar
        rate.rating = 3.7f
        val imageList = findViewById<LinearLayout>(R.id.imageList)
        val inflate = LayoutInflater.from(this)
        for (i in 0..7)
        {
            val view = inflate.inflate(R.layout.imageitem, imageList, false)
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            imageView.setImageResource(R.mipmap.ic_launcher)
            imageList.addView(view)
        }
    }
}