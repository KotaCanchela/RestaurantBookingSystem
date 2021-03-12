package com.cs990.restaurantbookingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.Adapters.ProfileItemAdapter
import com.cs990.restaurantbookingapp.databinding.FragmentThirdBinding
import com.cs990.restaurantbookingapp.models.ProfileItem

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //View bindings
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    //adapter and recyclerview for list
    private lateinit var profileListView: RecyclerView
    lateinit var itemAdapter: ProfileItemAdapter

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
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        profileListView = binding.profileListView

        listView()

        return binding.root
    }


    /**
     * function which defines the listView, the adapter used
     */

    fun listView(){
        //set the layout manager that the recycler view will use
        profileListView.layoutManager = LinearLayoutManager(this.requireActivity())
        // adapter class is initialized and list is passed in the parameter
        itemAdapter = ProfileItemAdapter(this.requireActivity(), getItemsList())
        // adapter instance is set to the recyclerview to inflate the items
        profileListView.adapter = itemAdapter

    }

    /**
     * List of items being displayed
     */

    private fun getItemsList(): ArrayList<ProfileItem> {
        val list = ArrayList<ProfileItem>()

        list.add(ProfileItem("Saved Restaurants", R.drawable.ic_baseline_restaurant_24))
        list.add(ProfileItem("Bookings", R.drawable.ic_baseline_menu_book_24))
        list.add(ProfileItem("Requests", R.drawable.ic_baseline_chat_bubble_24))
        list.add(ProfileItem("Payment Details", R.drawable.ic_baseline_credit_card_24))
        list.add(ProfileItem("Your ratings", R.drawable.ic_baseline_star_rate_24))
        list.add(ProfileItem("Logout", R.drawable.ic_baseline_exit_to_app_24))

        return list
    }




}

