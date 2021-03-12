package com.cs990.restaurantbookingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.Adapters.RestaurantItemAdapter
import com.cs990.restaurantbookingapp.databinding.FragmentSecondBinding
import com.cs990.restaurantbookingapp.models.RestaurantItem
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
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

    //Firestore
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var restaurantRef: CollectionReference = db.collection("Restaurants")

    //adapter
    lateinit var restaurantAdapter: RestaurantItemAdapter


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


        // Don't forget this!
        setupRecyclerView()

        return binding.root
    }


    fun setupRecyclerView(){

        //Query
        var query: Query = restaurantRef.orderBy("priority", Query.Direction.DESCENDING)

//        restaurantRef
//            .get()
//            .addOnSuccessListener { document ->
//            if (document != null) {
//                Toast.makeText(
//                    this.requireContext(),"Success and full",
//                    Toast.LENGTH_LONG).show()
//            } else {
//                Toast.makeText(
//                    this.requireContext(),"Success but empty",
//                    Toast.LENGTH_LONG).show()
//            }
//        }
//            .addOnFailureListener { exception ->
//                Toast.makeText(
//                    this.requireContext(),"you are a failure",
//                    Toast.LENGTH_LONG).show()
//            }

        //RecyclerOptions
        var options: FirestoreRecyclerOptions<RestaurantItem> = FirestoreRecyclerOptions.Builder<RestaurantItem>()
            .setQuery(query, RestaurantItem::class.java)
                .build()

        restaurantAdapter = RestaurantItemAdapter(this.requireContext(), options)
        //ViewHolder
        recyclerView = binding.rvRestaurantList

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




    private fun getRestaurantList() : ArrayList<RestaurantItem>{
        val restaurantList = ArrayList<RestaurantItem>()

//        restaurantList.add(RestaurantItem("McDonalds","3 kM",5,R.drawable.ic_mcdonalds))
//        restaurantList.add(RestaurantItem("Bucks Bar","23 kM",3,R.drawable.ic_restaurant))
//        restaurantList.add(RestaurantItem("Maggie Mays","7 kM",4,R.drawable.ic_restaurant))
//        restaurantList.add(RestaurantItem("Five Guys","3 kM",2,R.drawable.ic_restaurant))
//        restaurantList.add(RestaurantItem("Pizza Punks","43 kM",3,R.drawable.ic_restaurant))
//        restaurantList.add(RestaurantItem("Topolabama","13 kM",3,R.drawable.ic_restaurant))
//        restaurantList.add(RestaurantItem("Bread Meats Bread","3 kM",2,R.drawable.ic_restaurant))
//        restaurantList.add(RestaurantItem("Gamba","3 kM",2,R.drawable.ic_restaurant))
//        restaurantList.add(RestaurantItem("Alston Bar & Beef","43 kM",3,R.drawable.ic_restaurant))
//        restaurantList.add(RestaurantItem("Picnic","13 kM",3,R.drawable.ic_restaurant))
//        restaurantList.add(RestaurantItem("Mini Grill Steakhouse","3 kM",2,R.drawable.ic_restaurant))

        return restaurantList
    }

}