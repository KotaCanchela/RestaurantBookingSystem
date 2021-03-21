package com.cs990.restaurantbookingapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.adapters.ProfileItemAdapter
import com.cs990.restaurantbookingapp.databinding.FragmentThirdBinding
import com.cs990.restaurantbookingapp.loginAndRegister.LoginActivity
import com.cs990.restaurantbookingapp.models.ProfileItem
import com.cs990.restaurantbookingapp.profilePages.MyBookings
import com.cs990.restaurantbookingapp.profilePages.MyFavourites
import com.cs990.restaurantbookingapp.profilePages.MyRequests
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_third.*
import kotlinx.android.synthetic.main.fragment_third.myToolbar

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
    private lateinit var username: TextView
    private var currentUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!

    //View bindings
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    //adapter and recyclerview for list
    private lateinit var profileListView: RecyclerView
    lateinit var itemAdapter: ProfileItemAdapter

    //Firestore Username
    private lateinit var usernameText: TextView
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var dbUsernameRef: CollectionReference = db.collection("Users")
    private lateinit var usernameQuery: Task<DocumentSnapshot>

    //toolbar
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
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        profileListView = binding.profileListView

        usernameText = binding.usernameText

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

    override fun onStart() {
        super.onStart()
        if(!toolbarIsInstanciated) {
            toolbar()
        }
    }
    fun toolbar() {
        toolbarIsInstanciated = true
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
    /**
     * List of items being displayed
     */

    private fun getItemsList(): ArrayList<ProfileItem> {
        val list = ArrayList<ProfileItem>()

        list.add(ProfileItem("Favourites", R.drawable.ic_baseline_restaurant_24))
        list.add(ProfileItem("Bookings", R.drawable.ic_baseline_menu_book_24))
        list.add(ProfileItem("Requests", R.drawable.ic_baseline_chat_bubble_24))
        list.add(ProfileItem("Payment Details", R.drawable.ic_baseline_credit_card_24))
        list.add(ProfileItem("Your ratings", R.drawable.ic_baseline_star_rate_24))
        list.add(ProfileItem("Logout", R.drawable.ic_baseline_exit_to_app_24))

        return list
    }




}

