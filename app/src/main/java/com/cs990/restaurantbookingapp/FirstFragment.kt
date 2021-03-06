package com.cs990.restaurantbookingapp

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.adapters.RestaurantCuisineHomeAdapter
import com.cs990.restaurantbookingapp.databinding.FragmentFirstBinding
import com.cs990.restaurantbookingapp.loginAndRegister.LoginActivity
import com.cs990.restaurantbookingapp.models.RestaurantItem
import com.cs990.restaurantbookingapp.profilePages.MyBookings
import com.cs990.restaurantbookingapp.profilePages.MyFavourites
import com.cs990.restaurantbookingapp.profilePages.MyRequests
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_searchfilter.*
import kotlinx.android.synthetic.main.fragment_first.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    //recyclerView
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerView2: RecyclerView

    //Firestore
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var query: CollectionReference = db.collection("Restaurants")


    //Firestore Username
    private lateinit var usernameText: TextView
    private lateinit var currentUser: FirebaseUser
    private var dbUsernameRef: CollectionReference = db.collection("Users")
    private lateinit var usernameQuery: Task<DocumentSnapshot>



    //adapter
    lateinit var restaurantAdapter: RestaurantHomeAdapter
    lateinit var restaurantAdapter2: RestaurantCuisineHomeAdapter

    var toolbarIsInstanciated: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        recyclerView = binding.rvHomeRestaurant

        recyclerView2 = binding.rvCuisineHome

        usernameText = binding.usernameText

        setupRecyclerView()
        setupRecyclerView2()


        return binding.root

    }

    fun setupRecyclerView2() {

        //Query

        // If you look in the logcat (run tab) you will see the data coming from the database
        query
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }

        //RecyclerOptions
        var options: FirestoreRecyclerOptions<RestaurantItem> =
            FirestoreRecyclerOptions.Builder<RestaurantItem>()
                .setQuery(query, RestaurantItem::class.java)
                .build()


        //ViewHolder
        restaurantAdapter2 =
            RestaurantCuisineHomeAdapter(this.requireContext(), options)

        val layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerView2.layoutManager = layoutManager

        recyclerView2.adapter = restaurantAdapter2

    }

    fun setupRecyclerView() {
        //Query

        // If you look in the logcat (run tab) you will see the data coming from the database
        query
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }

        //RecyclerOptions
        var options: FirestoreRecyclerOptions<RestaurantItem> =
            FirestoreRecyclerOptions.Builder<RestaurantItem>()
                .setQuery(query, RestaurantItem::class.java)
                .build()

        //ViewHolder
        restaurantAdapter = RestaurantHomeAdapter(this.requireContext(), options)

        val layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = restaurantAdapter

    }




    override fun onStart() {
        super.onStart()
        if(!toolbarIsInstanciated) {
            toolbarIsInstanciated = true

            restaurantAdapter.startListening()
            restaurantAdapter2.startListening()
            myToolbar.setNavigationOnClickListener {
                super.onCreate(null)
            }

            //Dynamic username display
            currentUser = FirebaseAuth.getInstance().currentUser!!

            dbUsernameRef.document(currentUser.uid).get().addOnCompleteListener{ task ->
                if(task.isSuccessful) {

                    usernameText.text = task.result?.get("password").toString()

                } else {
                    Toast.makeText(this.requireContext(), "An error has occurred when looking for your username", Toast.LENGTH_SHORT).show()
                }
            }

            myToolbar.inflateMenu(R.menu.menu_home)
            myToolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_request -> {
                        super.onCreate(null)
                        val goRequests = Intent(this.requireContext(), MyRequests::class.java)
                        startActivity(goRequests)

                        true
                    }
                    R.id.action_book -> {
                        super.onCreate(null)
                        val goBook = Intent(this.requireContext(), MyBookings::class.java)
                        startActivity(goBook)

                        true
                    }
                    R.id.action_favourite -> {
                        super.onCreate(null)
                        val goFavourite = Intent(this.requireContext(), MyFavourites::class.java)
                        startActivity(goFavourite)

                        true
                    }
                    R.id.action_log_out -> {
                        val log = Intent(this.requireContext(), LoginActivity::class.java)
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

        override fun onStop() {
            super.onStop()
            restaurantAdapter.stopListening()
            restaurantAdapter2.startListening()

        }

    }


