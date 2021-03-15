package com.cs990.restaurantbookingapp

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.adapters.RestaurantCuisineHomeAdapter
import com.cs990.restaurantbookingapp.databinding.FragmentFirstBinding
import com.cs990.restaurantbookingapp.models.RestaurantItem
import com.cs990.restaurantbookingapp.models.RestaurantItems
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_searchfilter.*
import kotlinx.android.synthetic.main.fragment_first.*
import android.widget.ArrayAdapter as ArrayAdapter

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

    //adapter
    lateinit var restaurantAdapter: RestaurantHomeAdapter
    lateinit var restaurantAdapter2: RestaurantCuisineHomeAdapter


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

        // init recyclerView
        setupRecyclerView()
        setupRecyclerView2()
        //setupSpinner()


        return binding.root

    }

    //TODO: Finish implementing this: NullPointerException ERROR
    /*
    fun setupSpinner() {
        //spinners
        val spinner = binding.spinnerParty
      val adapter = ArrayAdapter(activity as Context,R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.number_of_people)) as SpinnerAdapter?
        spinner.adapter = adapter

        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Error")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                Toast.makeText(activity, type, Toast.LENGTH_LONG).show()
                println(type)
            }

        }

    }

     */
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
        restaurantAdapter.startListening()
        restaurantAdapter2.startListening()

    }

    override fun onStop() {
        super.onStop()
        restaurantAdapter.stopListening()
        restaurantAdapter2.startListening()

    }

}


