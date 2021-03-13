package com.cs990.restaurantbookingapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.LoginActivity
import com.cs990.restaurantbookingapp.R
import com.cs990.restaurantbookingapp.models.ProfileItem
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
            if(position == 5) {
                FirebaseAuth.getInstance().signOut()
                val loginPage = Intent(context, LoginActivity::class.java)
                context.startActivity(loginPage)
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