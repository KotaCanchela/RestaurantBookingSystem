package com.cs990.restaurantbookingapp.profilePages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.R
import com.cs990.restaurantbookingapp.adapters.BookingItemAdapter
import com.cs990.restaurantbookingapp.adapters.ProfileItemAdapter
import com.cs990.restaurantbookingapp.adapters.RestaurantItemAdapter
import com.cs990.restaurantbookingapp.databinding.ActivityMainBinding
import com.cs990.restaurantbookingapp.databinding.ActivityMyBookingsBinding
import com.cs990.restaurantbookingapp.models.BookingItem
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class MyBookings : AppCompatActivity() {

    private lateinit var binding: ActivityMyBookingsBinding

    //recyclerview for list
    lateinit var recyclerView: RecyclerView

    //Firestore
    private var currentUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    var query: CollectionReference = db
        .collection("Users")
        .document(currentUser.uid)
        .collection("Bookings")

    //adapter
    lateinit var bookingAdapter: BookingItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMyBookingsBinding.inflate(layoutInflater)
        val view = binding.root
        setupUI()
        setContentView(view)


        setupRecyclerView()
    }


    fun setupUI() {
        recyclerView = binding.bookingList
    }


    fun setupRecyclerView(){

        val options: FirestoreRecyclerOptions<BookingItem> = FirestoreRecyclerOptions.Builder<BookingItem>()
            .setQuery(query, BookingItem::class.java)
            .build()

        bookingAdapter = BookingItemAdapter(this, options)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = bookingAdapter

    }

    override fun onStart() {
        super.onStart()
        bookingAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        bookingAdapter.stopListening()
    }


}