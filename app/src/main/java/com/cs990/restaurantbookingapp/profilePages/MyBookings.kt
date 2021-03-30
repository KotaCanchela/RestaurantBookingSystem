package com.cs990.restaurantbookingapp.profilePages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.R
import com.cs990.restaurantbookingapp.adapters.BookingItemAdapter
import com.cs990.restaurantbookingapp.adapters.ProfileItemAdapter
import com.cs990.restaurantbookingapp.adapters.RestaurantItemAdapter
import com.cs990.restaurantbookingapp.databinding.ActivityMainBinding
import com.cs990.restaurantbookingapp.databinding.ActivityMyBookingsBinding
import com.cs990.restaurantbookingapp.loginAndRegister.LoginActivity
import com.cs990.restaurantbookingapp.models.BookingItem
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_second.*

/**
 *  An Android Layout Class that provides functionality for the list of current user bookings.
 * @author Group 1
 * @version 1.0
 */
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

    //Firestore Username
    private lateinit var usernameText: TextView
    private var dbUsernameRef: CollectionReference = db.collection("Users")
    private lateinit var usernameQuery: Task<DocumentSnapshot>

    //toolbar
    var toolbarIsInstanciated: Boolean = false

    /**
     * onCreate method for this Layout.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyBookingsBinding.inflate(layoutInflater)
        val view = binding.root
        setupUI()
        setContentView(view)
        setupRecyclerView()
    }

    /**
     * Configures bindings for the current session
     */
    private fun setupUI() {
        recyclerView = binding.bookingList
        usernameText = binding.usernameText
    }

    /**
     * Retrieves booking items from firebase and adds them to the displayed list
     */
    private fun setupRecyclerView(){

        val options: FirestoreRecyclerOptions<BookingItem> = FirestoreRecyclerOptions.Builder<BookingItem>()
            .setQuery(query, BookingItem::class.java)
            .build()

        bookingAdapter = BookingItemAdapter(this, options)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = bookingAdapter
    }

    /**
     * Configures Action Listener for this layout and configures the toolbar upon detecting
     * instanciation.
     */
    override fun onStart() {
        super.onStart()
        bookingAdapter.startListening()

        if(!toolbarIsInstanciated) {
            toolbarIsInstanciated = true
            myToolbar.setNavigationOnClickListener {
                super.onCreate(null)
            }
            //Dynamic username display
            currentUser = FirebaseAuth.getInstance().currentUser!!
            dbUsernameRef.document(currentUser.uid).get().addOnCompleteListener{ task ->
                if(task.isSuccessful) {
                    usernameText.text = task.result?.get("password").toString()

                } else {
                    Toast.makeText(this, "An error has occurred when looking for your username", Toast.LENGTH_SHORT).show()
                }
            }

            //Toolbar setup
            myToolbar.inflateMenu(R.menu.menu_home)
            myToolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_request -> {
                        super.onCreate(null)
                        val goRequests = Intent(this, MyRequests::class.java)
                        startActivity(goRequests)

                        true
                    }
                    R.id.action_book -> {
                        super.onCreate(null)
                        val goBook = Intent(this, MyBookings::class.java)
                        startActivity(goBook)

                        true
                    }
                    R.id.action_favourite -> {
                        super.onCreate(null)
                        val goFavourite = Intent(this, MyFavourites::class.java)
                        startActivity(goFavourite)

                        true
                    }
                    R.id.action_log_out -> {
                        val log = Intent(this, LoginActivity::class.java)
                        FirebaseAuth.getInstance().signOut()
                        startActivity(log)

                        true
                    }
                    else -> {
                        super.onOptionsItemSelected(it)
                    }
                }
            }
        }
    }

    /**
     * Disables Action Listener onStop.
     */
    override fun onStop() {
        super.onStop()
        bookingAdapter.stopListening()
    }
}