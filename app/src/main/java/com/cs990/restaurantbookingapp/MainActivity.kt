package com.cs990.restaurantbookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cs990.restaurantbookingapp.databinding.ActivityMainBinding
import com.cs990.restaurantbookingapp.firebase.FirestoreClass
import com.cs990.restaurantbookingapp.loginAndRegister.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding;


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val myToolbar = findViewById<Toolbar>(R.id.myToolbar)
        setSupportActionBar(myToolbar)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.BottomNavigationView)
        val navController = findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)



        var currentUserID = FirestoreClass().getCurrentUserId()

        if (currentUserID.isNotEmpty()) {
            Toast.makeText(
                this,"Welcome to RestaurantBooker",
                Toast.LENGTH_LONG).show()
        } else {
            val loginPage = Intent(this, LoginActivity::class.java)
            startActivity(loginPage)
            finish()
        }
    }



}