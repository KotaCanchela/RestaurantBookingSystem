package com.cs990.restaurantbookingapp.restaurantPage

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.cs990.restaurantbookingapp.BaseActivity
import com.cs990.restaurantbookingapp.MainActivity
import com.cs990.restaurantbookingapp.R
import com.cs990.restaurantbookingapp.models.BookingItem
import com.cs990.restaurantbookingapp.profilePages.MyBookings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*
import kotlin.concurrent.schedule

class BookingConfirmationActivity : BaseActivity() {

    private lateinit var bookingString: String
    private lateinit var bookingItem: BookingItem
    private lateinit var timeDate: TextView
    private lateinit var home: Button
    private lateinit var myBookings: Button
    private lateinit var cancelBookingBtn: Button
    private lateinit var mProgressDialog: Dialog

    private var currentUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var bookingRef: CollectionReference = db.collection("Users").document(currentUser.uid)
            .collection("Bookings")
    private lateinit var query: Query
    private var bookingID: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_confirmation)

        var intent: Intent = intent
        bookingString = intent.extras?.getString("timeString")!!
        bookingItem = intent.getParcelableExtra<BookingItem>("bookingItem")!!

        setupUI()

        query = bookingRef.whereEqualTo("restaurantItem.name", bookingItem.getRestaurantItem().getName())

        query.get().addOnCompleteListener{
            if(it.result?.isEmpty == false) {

                for (document in it.result!!) {
                    bookingID = document.id
                }
            } else {
                Toast.makeText(this, "An error has occurred when looking for you booking", Toast.LENGTH_SHORT).show()
            }
        }

        setupListeners()



    }

    private fun goHome(){

        val home = Intent(this, MainActivity::class.java)
        this.startActivity(home)
    }


    private fun setupUI(){
        cancelBookingBtn = findViewById(R.id.btn_cancel_booking)
        timeDate = findViewById(R.id.TimeAndDate)
        home = findViewById(R.id.btn_home)
        myBookings = findViewById(R.id.btn_my_bookings)
        timeDate.text = bookingString
    }

    private fun setupListeners(){

        home.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
            finish()
        }

        myBookings.setOnClickListener{
            val myBookings = Intent(this, MyBookings::class.java)
            this.startActivity(myBookings)
            finish()
        }

        cancelBookingBtn.setOnClickListener{
            if(bookingID != null){
                bookingRef.document(bookingID!!).delete()
                Toast.makeText(this, "Booking cancelled", Toast.LENGTH_SHORT).show()
                Handler(mainLooper).postDelayed(Runnable {
                    goHome()
                }, 1000)


            } else {
                Toast.makeText(this, "Error when cancelling your booking", Toast.LENGTH_SHORT).show()
            }
        }
    }



}