package com.cs990.restaurantbookingapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.databinding.FragmentSecondBinding
import com.cs990.restaurantbookingapp.databinding.FragmentThirdBinding

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

        // Don't forget this!
        initListView()

        return binding.root
    }
    fun initListView(){
        recyclerView.layoutManager = LinearLayoutManager(this.requireActivity())

        rvAdapter = RecyclerView_Adapter(this.requireActivity(), getRestaurantList())

        recyclerView.adapter = rvAdapter
    }

    private fun getRestaurantList() : ArrayList<RestaurantItems>{
        val restaurantList = ArrayList<RestaurantItems>()

        restaurantList.add(RestaurantItems("McDonalds","3 kM",5,R.drawable.ic_mcdonalds))
        restaurantList.add(RestaurantItems("McDonalds","3 kM",5,R.drawable.ic_mcdonalds))
        restaurantList.add(RestaurantItems("McDonalds","3 kM",5,R.drawable.ic_mcdonalds))
        restaurantList.add(RestaurantItems("McDonalds","3 kM",5,R.drawable.ic_mcdonalds))
        restaurantList.add(RestaurantItems("McDonalds","3 kM",5,R.drawable.ic_mcdonalds))
        restaurantList.add(RestaurantItems("McDonalds","3 kM",5,R.drawable.ic_mcdonalds))
        restaurantList.add(RestaurantItems("McDonalds","3 kM",5,R.drawable.ic_mcdonalds))

        return restaurantList
    }


    /*
    private class MyCustomAdapter(context: Context) : BaseAdapter() {

        private val mContext: Context = context
        var searchItems = arrayListOf("McDonalds", "Maggie Mays", "Bucks Bar", "Pizza Punks", "Five Guys", "Bread Meats Bread","Topolabama")
        var rating = arrayListOf(4,3,4,4,3,4,4)
        var distance = arrayListOf("10km","7 km","12 km","17 km","10km","2 km","6 km")
        var restaurantPicture = arrayListOf(R.drawable.ic_mcdonalds,R.drawable.ic_restaurant,R.drawable.ic_restaurant,R.drawable.ic_restaurant,R.drawable.ic_restaurant,R.drawable.ic_restaurant, R.drawable.ic_restaurant)


        override fun getCount(): Int {
            return searchItems.size
        }

        override fun getItem(position: Int): Any {
            return "test string"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, viewgroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.search_layout, viewgroup, false)

            val restaurantTextView = rowMain.findViewById<TextView>(R.id.tv_title)
            restaurantTextView.text = searchItems[position]

            val restaurantRating = rowMain.findViewById<RatingBar>(R.id.rb_ratingBar)
            restaurantRating.numStars = rating[position]

            val restaurantDisTextView = rowMain.findViewById<TextView>(R.id.tv_distance)
            restaurantDisTextView.text = distance[position]

            val restaurantImageView = rowMain.findViewById<ImageView>(R.id.iv_image)
            restaurantImageView.setImageResource(restaurantPicture[position])

            return rowMain
        }
    }

     */
}