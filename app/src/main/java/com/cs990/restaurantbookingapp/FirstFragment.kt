package com.cs990.restaurantbookingapp

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.adapters.RestaurantCuisineHomeAdapter
import com.cs990.restaurantbookingapp.databinding.FragmentFirstBinding
import com.cs990.restaurantbookingapp.models.RestaurantItem
import com.cs990.restaurantbookingapp.models.RestaurantItems
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
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


        return binding.root


    }

    fun setupRecyclerView() {

        //Query
        /*

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

         */


        //ViewHolder
        restaurantAdapter2 = RestaurantCuisineHomeAdapter(this.requireContext(), getCuisineList())


        val layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerView2.layoutManager = layoutManager
        //recyclerViews.layoutManager = layoutManager

        recyclerView2.adapter = restaurantAdapter2
        //recyclerViews.adapter = restaurantAdapters

    }
    fun setupRecyclerView2() {

        //ViewHolder
        restaurantAdapter = RestaurantHomeAdapter(this.requireContext(), getRestaurantList())

        val layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerView.layoutManager = layoutManager
        //recyclerViews.layoutManager = layoutManager

        recyclerView.adapter = restaurantAdapter
        //recyclerViews.adapter = restaurantAdapters

    }
    /*

  override fun onStart() {
      super.onStart()
      restaurantAdapter.startListening()
  }

  override fun onStop() {
      super.onStop()
      restaurantAdapter.stopListening()
  }
  */

  private fun getRestaurantList() : ArrayList<RestaurantItems>{
     val restaurantList = ArrayList<RestaurantItems>()

      restaurantList.add(RestaurantItems("McDonalds",5,R.drawable.ic_restaurant))
      restaurantList.add(RestaurantItems("Bucks Bar",3,R.drawable.ic_restaurant))
      restaurantList.add(RestaurantItems("Maggie Mays",4,R.drawable.ic_restaurant))
      restaurantList.add(RestaurantItems("Five Guys",2,R.drawable.ic_restaurant))
      restaurantList.add(RestaurantItems("Pizza Punks",3,R.drawable.ic_restaurant))
      restaurantList.add(RestaurantItems("Topolabama",3,R.drawable.ic_restaurant))
      restaurantList.add(RestaurantItems("Bread Meats Bread",2,R.drawable.ic_restaurant))
      restaurantList.add(RestaurantItems("Gamba",2,R.drawable.ic_restaurant))
      restaurantList.add(RestaurantItems("Alston Bar & Beef",3,R.drawable.ic_restaurant))
      restaurantList.add(RestaurantItems("Picnic",3,R.drawable.ic_restaurant))
     restaurantList.add(RestaurantItems("Mini Grill Steakhouse",2,R.drawable.ic_restaurant))

      return restaurantList
  }

    private fun getCuisineList() : ArrayList<RestaurantItems>{
        val restaurantList = ArrayList<RestaurantItems>()

        restaurantList.add(RestaurantItems("Italian",5,R.drawable.italian))
        restaurantList.add(RestaurantItems("American",3,R.drawable.gourmet_food))
        restaurantList.add(RestaurantItems("Mexican",4,R.drawable.italian))
        restaurantList.add(RestaurantItems("Japanese",2,R.drawable.mirazur_world_2019_dish1_min))
        restaurantList.add(RestaurantItems("Korean",3,R.drawable.full_traditional_scottish_breakfast_lauripatterson))
        restaurantList.add(RestaurantItems("Steak House",3,R.drawable.italian))

        return restaurantList
    }


}