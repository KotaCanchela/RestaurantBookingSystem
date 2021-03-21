package com.cs990.restaurantbookingapp.adapters

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.*
import com.cs990.restaurantbookingapp.loginAndRegister.LoginActivity
import com.cs990.restaurantbookingapp.models.ProfileItem
import com.cs990.restaurantbookingapp.profilePages.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.row_profile.view.*

class ProfileItemAdapter(val context: Context, val items: ArrayList<ProfileItem>):
    RecyclerView.Adapter<ProfileItemAdapter.ProfileViewHolder>() {


    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.row_profile,
                parent,
                false
            )
        )
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */

    override fun onBindViewHolder(holderProfile: ProfileViewHolder, position: Int) {
        val item = items[position]

        holderProfile.profileItemText.row_text.text = item.getText()
        holderProfile.profileItemImage.row_image.setImageDrawable(getDrawable(context, item.getIcon()))

        holderProfile.itemView.setOnClickListener{
            if(position == 0) {
                val mySavedRestaurants = Intent(context, MyFavourites::class.java)
                context.startActivity(mySavedRestaurants)
//                context.finish()
            }
            if(position == 1) {
                val myBookings = Intent(context, MyBookings::class.java)
                context.startActivity(myBookings)
//                context.finish()
            }
            if(position == 2) {
                val myPaymentActivity = Intent(context, MyPaymentActivity::class.java)
                context.startActivity(myPaymentActivity)
//                context.finish()
            }
            if(position == 3) {
                val myRatings = Intent(context, MyRatings::class.java)
                context.startActivity(myRatings)
//                context.finish()
            }
            if(position == 4) {
                Toast.makeText(context, "Successfully logged out", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    FirebaseAuth.getInstance().signOut()
                    val logout = Intent(context, LoginActivity::class.java)
                    context.startActivity(logout)
                }, 500)
//                context.finish()
            }
        }

    }


    /**
     * Returns the size of the items list
     */
    override fun getItemCount(): Int {
        return items.size
    }


    /**
     * Inner class that describes an item view
     */

    class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val profileItemText: TextView = view.profile_item.row_text
        val profileItemImage: ImageView = view.profile_item.row_image
    }
}