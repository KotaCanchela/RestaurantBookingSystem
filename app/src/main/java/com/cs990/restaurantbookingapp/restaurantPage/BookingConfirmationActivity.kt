package com.cs990.restaurantbookingapp.restaurantPage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.cs990.restaurantbookingapp.BaseActivity
import com.cs990.restaurantbookingapp.MainActivity
import com.cs990.restaurantbookingapp.R

class BookingConfirmationActivity : BaseActivity() {

    private lateinit var bookingString: String
    private lateinit var timeDate: TextView
    private lateinit var home: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_confirmation)

        var intent: Intent = intent
        bookingString = intent.extras?.getString("timeString")!!

        timeDate = findViewById(R.id.TimeAndDate)
        home = findViewById(R.id.btn_home)

        timeDate.text = bookingString
        home.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
            finish()
        }
    }
}