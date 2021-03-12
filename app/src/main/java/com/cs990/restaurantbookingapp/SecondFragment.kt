package com.cs990.restaurantbookingapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.R.id.searchRestaurant
import com.cs990.restaurantbookingapp.databinding.FragmentSecondBinding
import com.cs990.restaurantbookingapp.databinding.FragmentThirdBinding
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
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    //RecyclerView and adpater
    private lateinit var recyclerView: RecyclerView
    lateinit var rvAdapter: RecyclerView_Adapter

    //searchView
    private lateinit var search : SearchView

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

        //search view
        search = binding.searchRestaurant

        val searchTest = binding.root.findViewById<SearchView>(R.id.searchRestaurant)


        searchTest.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener,
                android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    rvAdapter.filter.filter(newText)
                    return false
                }

            },
        )

        // Don't forget this!
        initListView()
        initSearch()

        return binding.root
    }

    private fun initSearch() {


        val searchIcon = binding.root.findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.WHITE)


        val cancelIcon = binding.root.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.WHITE)


        val textView = binding.root.findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.WHITE)
    }

    fun initListView(){
        recyclerView.layoutManager = LinearLayoutManager(this.requireActivity())

        rvAdapter = RecyclerView_Adapter(this.requireActivity(), getRestaurantList())

        recyclerView.adapter = rvAdapter
    }


    private fun getRestaurantList() : ArrayList<RestaurantItems>{
        val restaurantList = ArrayList<RestaurantItems>()

        restaurantList.add(RestaurantItems("McDonalds","3 kM",5,R.drawable.ic_mcdonalds))
        restaurantList.add(RestaurantItems("Bucks Bar","23 kM",3,R.drawable.ic_restaurant))
        restaurantList.add(RestaurantItems("Maggie Mays","7 kM",4,R.drawable.ic_restaurant))
        restaurantList.add(RestaurantItems("Five Guys","3 kM",2,R.drawable.ic_restaurant))
        restaurantList.add(RestaurantItems("Pizza Punks","43 kM",3,R.drawable.ic_restaurant))
        restaurantList.add(RestaurantItems("Topolabama","13 kM",3,R.drawable.ic_restaurant))
        restaurantList.add(RestaurantItems("Bread Meats Bread","3 kM",2,R.drawable.ic_restaurant))
        restaurantList.add(RestaurantItems("Gamba","3 kM",2,R.drawable.ic_restaurant))
        restaurantList.add(RestaurantItems("Alston Bar & Beef","43 kM",3,R.drawable.ic_restaurant))
        restaurantList.add(RestaurantItems("Picnic","13 kM",3,R.drawable.ic_restaurant))
        restaurantList.add(RestaurantItems("Mini Grill Steakhouse","3 kM",2,R.drawable.ic_restaurant))

        return restaurantList
    }

}