package com.cs990.restaurantbookingapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.RatingBar
import android.widget.TextView
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
    private lateinit var restaurantListView: ListView

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
        restaurantListView = binding.searchListView

//        var listViewAdapter: ArrayAdapter<String> = ArrayAdapter(this.requireActivity(), android.R.layout.simple_list_item_1, profileMenuItems)
        restaurantListView.adapter = MyCustomAdapter(this.requireActivity())
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private class MyCustomAdapter(context: Context) : BaseAdapter() {

        private val mContext: Context = context
        var searchItems = arrayListOf("McDonalds", "Burger King", "KFC", "Pizza Hut", "Taco Bell", "Bread Meats Bread")
        var rating = arrayListOf(4,3,4,4,3,4)
        var distance = arrayListOf("3km","4 km","2 km","2.5 km","3 km","2 km")


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

            return rowMain
        }
    }
}