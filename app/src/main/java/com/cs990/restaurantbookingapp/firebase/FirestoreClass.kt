package com.cs990.restaurantbookingapp.firebase

import android.app.Activity
import android.util.Log
import com.cs990.restaurantbookingapp.loginAndRegister.LoginActivity
import com.cs990.restaurantbookingapp.loginAndRegister.RegisterActivity
import com.cs990.restaurantbookingapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity, userInfo: User) {
        mFireStore.collection("Users")
            .document(getCurrentUserId()).set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
            }
    }

    fun getCurrentUserId(): String {
        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null){
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    fun signInUser(activity: LoginActivity){
        mFireStore.collection("Users")
        .document(getCurrentUserId())
        .get()
        .addOnSuccessListener { document ->
            val loggedInUser = document.toObject(User::class.java)!!

            activity.loginSuccess(loggedInUser)
        }.addOnFailureListener {
            e -> Log.e("SignInUser", "Error writing document", e)
            }
    }

    fun getRestaurantsList(activity: Activity){
        mFireStore.collection("Restaurants")
            .whereArrayContains("Assigned to", getCurrentUserId())
    }

}