package com.cs990.restaurantbookingapp.loginAndRegister

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.cs990.restaurantbookingapp.BaseActivity
import com.cs990.restaurantbookingapp.MainActivity
import com.cs990.restaurantbookingapp.loginAndRegister.RegisterActivity
import com.cs990.restaurantbookingapp.databinding.ActivityLoginBinding
import com.cs990.restaurantbookingapp.models.User
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    lateinit var email: String
    lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(FirebaseAuth.getInstance().currentUser != null) {
            val homePage = Intent(this, MainActivity::class.java)
            startActivity(homePage)
            finish()
        }


        setUIViews()
        setClickListeners()


    }

    fun loginSuccess(user: User){
        hideProgressDialog()
        val homePage = Intent(this, MainActivity::class.java)
        startActivity(homePage)
        finish()
    }

    private fun signInRegisteredUser() {
        email = binding.editEmail.text.toString().trim{it <= ' '}
        password = binding.editPassword.text.toString().trim{it <= ' '}

        if(validateForm(email, password)) {
            showProgressDialog("Please wait")

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    hideProgressDialog()

                    if (task.isSuccessful) {

                        Toast.makeText(
                            this,
                            "You have successfully signed in.",
                            Toast.LENGTH_LONG
                        ).show()

                        val homePage = Intent(this, MainActivity::class.java)
                        startActivity(homePage)
                        finish()

                    } else {

                        Toast.makeText(
                            this,"Sign in failed",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }
    }


    private fun validateForm( email: String, password: String): Boolean{
        return when {
            TextUtils.isEmpty(email) ->{
                showErrorSnackBar("Please enter a username")
                false
            }
            TextUtils.isEmpty(password) ->{
                showErrorSnackBar("Please enter a password")
                false
            } else -> {
                true
            }
        }
    }


    fun setUIViews(){


    }

    fun setClickListeners() {

        binding.editEmail.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                s!!
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s!!
            }

            override fun afterTextChanged(s: Editable?) {
                email = s.toString()!!
            }
        })

        binding.editPassword.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                s!!
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s!!
            }

            override fun afterTextChanged(s: Editable?) {
                password = s.toString()!!
            }
        })

        binding.loginButton.setOnClickListener {
            signInRegisteredUser()

        }

        binding.registerText2.setOnClickListener {

            val homePage = Intent(this, RegisterActivity::class.java)
            startActivity(homePage)
            finish()

        }
    }


    fun register(view: View) {
        val registerPage = Intent(this, RegisterActivity::class.java)
        startActivity(registerPage)
        finish()

    }
}