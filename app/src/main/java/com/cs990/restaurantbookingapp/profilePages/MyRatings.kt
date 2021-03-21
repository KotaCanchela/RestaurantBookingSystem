package com.cs990.restaurantbookingapp.profilePages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.cs990.restaurantbookingapp.R
import com.cs990.restaurantbookingapp.loginAndRegister.LoginActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_second.*

class MyRatings : AppCompatActivity() {


    //Firestore
    private var currentUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    //Firestore Username
    private lateinit var usernameText: TextView
    private var dbUsernameRef: CollectionReference = db.collection("Users")
    private lateinit var usernameQuery: Task<DocumentSnapshot>

    //toolbar
    var toolbarIsInstanciated: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_ratings)

        usernameText = findViewById(R.id.usernameText)
    }
    override fun onStart() {
        super.onStart()


        if(!toolbarIsInstanciated) {
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
                    Toast.makeText(this, "An error has occurred when looking for your username", Toast.LENGTH_SHORT).show()
                }
            }

            //Toolbar setup
            myToolbar.inflateMenu(R.menu.menu_home)
            myToolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_request -> {
                        super.onCreate(null)
                        val goRequests = Intent(this, MyRequests::class.java)
                        startActivity(goRequests)

                        true
                    }
                    R.id.action_book -> {
                        super.onCreate(null)
                        val goBook = Intent(this, MyBookings::class.java)
                        startActivity(goBook)

                        true
                    }
                    R.id.action_favourite -> {
                        super.onCreate(null)
                        val goFavourite = Intent(this, MyFavourites::class.java)
                        startActivity(goFavourite)

                        true
                    }
                    R.id.action_log_out -> {
                        val log = Intent(this, LoginActivity::class.java)
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
    }

}