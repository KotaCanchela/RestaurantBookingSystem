package com.cs990.restaurantbookingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.R
import kotlinx.android.synthetic.main.restaurant_page_image.view.*

class RestaurantPageImageAdapter(val context: Context, val items: ArrayList<Int>):
    RecyclerView.Adapter<RestaurantPageImageAdapter.RestaurantPageImageViewHolder>() {

    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantPageImageViewHolder {
        return RestaurantPageImageViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.restaurant_page_image,
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

    override fun onBindViewHolder(holder: RestaurantPageImageViewHolder, position: Int) {
        val item = items[position]

        holder.restaurantPageImage.setImageResource(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    /**
     * Inner class that describes an item view
     */

    class RestaurantPageImageViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val restaurantPageImage: ImageView = view.restaurant_image.rest_image

    }

}
