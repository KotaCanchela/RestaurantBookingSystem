package com.cs990.restaurantbookingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
/**
 * An Android Activity Class that provides functionality for the Splash screen that appears when
 * the application is loaded
 * @author Group 1
 * @version 1.0
 */
class SplashScreenActivity : AppCompatActivity() {

    lateinit var handler: Handler

    /**
     * onCreate method for this Activity. Responsible for moving the user to the Main Activity (the
     * "home page") after a short delay.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000 )
    }
}