package com.cs990.restaurantbookingapp.profilePages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.R
import com.cs990.restaurantbookingapp.adapters.RestaurantItemAdapter
import com.cs990.restaurantbookingapp.databinding.ActivityMyBookingsBinding
import com.cs990.restaurantbookingapp.databinding.ActivityMyFavouritesBinding
import com.cs990.restaurantbookingapp.models.BookingItem
import com.cs990.restaurantbookingapp.models.RestaurantItem
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class MyFavourites : AppCompatActivity() {

    private lateinit var binding: ActivityMyFavouritesBinding

    //recyclerview for list
    lateinit var recyclerView: RecyclerView

    //Firestore
    private var currentUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    var query: CollectionReference = db
            .collection("Users")
            .document(currentUser.uid)
            .collection("Favourites")

    //adapter
    lateinit var restaurantAdapter: RestaurantItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyFavouritesBinding.inflate(layoutInflater)
        val view = binding.root
        setupUI()
        setContentView(view)


        setupRecyclerView()
    }


    private fun setupUI() {
        recyclerView = binding.favouriteList
    }

    private fun setupRecyclerView() {

        val options: FirestoreRecyclerOptions<RestaurantItem> = FirestoreRecyclerOptions.Builder<RestaurantItem>()
                .setQuery(query, RestaurantItem::class.java)
                .build()


        restaurantAdapter = RestaurantItemAdapter(this, options)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = restaurantAdapter
    }

    override fun onStart() {
        super.onStart()
        restaurantAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        restaurantAdapter.stopListening()
    }

}