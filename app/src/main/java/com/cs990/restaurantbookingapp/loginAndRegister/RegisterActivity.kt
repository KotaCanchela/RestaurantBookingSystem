package com.cs990.restaurantbookingapp.loginAndRegister

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.cs990.restaurantbookingapp.BaseActivity
import com.cs990.restaurantbookingapp.MainActivity
import com.cs990.restaurantbookingapp.databinding.ActivityRegisterBinding
import com.cs990.restaurantbookingapp.firebase.FirestoreClass
import com.cs990.restaurantbookingapp.loginAndRegister.LoginActivity
import com.cs990.restaurantbookingapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginText2.setOnClickListener{
            val loginPage = Intent(this, LoginActivity::class.java)
            startActivity(loginPage)
            finish()
        }

        binding.registerButton.setOnClickListener{
            registerUser()
            val homePage = Intent(this, MainActivity::class.java)
            startActivity(homePage)
            finish()
        }


    }

    private fun registerUser() {
        val username: String = binding.enterUsername.text.toString().trim(){it <= ' '}
        val email: String = binding.enterEmail.text.toString().trim(){it <= ' '}
        val password: String = binding.enterPassword.text.toString().trim(){it <= ' '}

        if(validateForm(username, email, password)){
            showProgressDialog("Please wait")
            FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    hideProgressDialog()

                    if (task.isSuccessful) {

                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        val registeredEmail = firebaseUser.email!!
                        val user = User(firebaseUser.uid, username, registeredEmail)
                        FirestoreClass().registerUser(this, user)
                    } else {

                        Toast.makeText(
                            this,"Registration failed",
                            Toast.LENGTH_LONG).show()
                    }
                }
        }

    }

    private fun validateForm(username: String, password: String, email: String): Boolean{
        return when {
            TextUtils.isEmpty(username) ->{
                showErrorSnackBar("Please enter a username")
                false
            }
            TextUtils.isEmpty(email) ->{
                showErrorSnackBar("Please enter an email address")
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

    fun userRegisteredSuccess(){
        Toast.makeText(
            this,
            "You have successfully registered",
            Toast.LENGTH_LONG).show()

        hideProgressDialog()
        FirebaseAuth.getInstance().signOut()
        finish()
    }

}