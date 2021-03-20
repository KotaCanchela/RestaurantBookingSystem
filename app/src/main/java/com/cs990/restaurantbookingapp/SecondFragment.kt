package com.cs990.restaurantbookingapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.adapters.RestaurantItemAdapter
import com.cs990.restaurantbookingapp.databinding.FragmentSecondBinding
import com.cs990.restaurantbookingapp.loginAndRegister.LoginActivity
import com.cs990.restaurantbookingapp.models.RestaurantItem
import com.cs990.restaurantbookingapp.profilePages.MyBookings
import com.cs990.restaurantbookingapp.profilePages.MyFavourites
import com.cs990.restaurantbookingapp.profilePages.MyRequests
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_second.*

import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.myToolbar


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
    private var searchQuery: Query = db.collection("Restaurants").orderBy("name").startAt("").endAt("\uf8ff")



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

        binding.btnSearch.setOnClickListener {


            refreshRecyclerView(binding.searchBar.query.toString())
            //hides keyboard
            val imm = view?.let { ContextCompat.getSystemService(it.context, InputMethodManager::class.java) }
            imm?.hideSoftInputFromWindow(view?.windowToken, 0)
        }


    }



    private fun refreshRecyclerView(searchText: String){

        restaurantAdapter.stopListening();

        searchQuery = db.collection("Restaurants").orderBy("name").startAt(searchText).endAt("$searchText\uf8ff")

        searchQuery.addSnapshotListener { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            val cities = ArrayList<String>()
            for (doc in value!!) {
                doc.getString("name")?.let {
                    cities.add(it)
                }
            }
            Log.d(TAG, "Current cites in CA: $cities")
        }



        var options: FirestoreRecyclerOptions<RestaurantItem> = FirestoreRecyclerOptions.Builder<RestaurantItem>()
                .setQuery(searchQuery, RestaurantItem::class.java)
                .build()


//        restaurantAdapter = RestaurantItemAdapter(this.requireContext(), options)

        restaurantAdapter.setItems(options)
        restaurantAdapter.notifyDataSetChanged()
        restaurantAdapter.startListening();
//        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
//
//        recyclerView.adapter = restaurantAdapter



    }


    fun setupRecyclerView(){


        //RecyclerOptions
        var options: FirestoreRecyclerOptions<RestaurantItem> = FirestoreRecyclerOptions.Builder<RestaurantItem>()
            .setQuery(searchQuery, RestaurantItem::class.java)
                .build()



        //ViewHolder

        restaurantAdapter = RestaurantItemAdapter(this.requireContext(), options)


        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())

        recyclerView.adapter = restaurantAdapter

    }

    override fun onStart() {
        super.onStart()
        myToolbar.setNavigationOnClickListener {
            super.onCreate(null)
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

        restaurantAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        restaurantAdapter.stopListening()
    }




}