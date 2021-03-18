package com.cs990.restaurantbookingapp

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.adapters.RestaurantItemAdapter
import com.cs990.restaurantbookingapp.databinding.FragmentSecondBinding
import com.cs990.restaurantbookingapp.models.RestaurantItem
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_second.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //View bindings
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    //recyclerview for list
    lateinit var recyclerView: RecyclerView
    lateinit var btnFilter: Button

    //Firestore
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var query: CollectionReference = db.collection("Restaurants")

    //adapter
    lateinit var restaurantAdapter: RestaurantItemAdapter

//    //Favourite button
//    private var favourite: Boolean = false


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
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        recyclerView = binding.rvRestaurantList

        setupRecyclerView()
        setupUI()

        return binding.root
    }

    fun setupUI(){
        binding.btnFilter.setOnClickListener(){

            val dialog = FilterDialog(this.requireContext())
            dialog.show()

            }

    }


    fun setupRecyclerView(){


        //RecyclerOptions
        var options: FirestoreRecyclerOptions<RestaurantItem> = FirestoreRecyclerOptions.Builder<RestaurantItem>()
            .setQuery(query, RestaurantItem::class.java)
                .build()


        //ViewHolder

        restaurantAdapter = RestaurantItemAdapter(this.requireContext(), options)

        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())

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