package com.cs990.restaurantbookingapp.firebase

import android.app.Activity
import android.util.Log
import com.cs990.restaurantbookingapp.loginAndRegister.LoginActivity
import com.cs990.restaurantbookingapp.loginAndRegister.RegisterActivity
import com.cs990.restaurantbookingapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

/**
 *  Control Class for Firebase access. Generates a login session that is used for pulling values
 *  from the Database
 * @author Group 1
 * @version 1.0
 */
class FirestoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    /**
     * Registers a new firebase user.
     */
    fun registerUser(activity: RegisterActivity, userInfo: User) {
        mFireStore.collection("Users")
            .document(getCurrentUserId()).set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
            }
    }

    /**
     * Gets the current user logged in
     * @return  UserId
     */
    fun getCurrentUserId(): String {
        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null){
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    /**
     * Signs a user into the application from the login Activity
     */
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

    /**
     * Returns a list of restaurant's stored in the database
     */
    fun getRestaurantsList(activity: Activity){
        mFireStore.collection("Restaurants")
            .whereArrayContains("Assigned to", getCurrentUserId())
    }

}