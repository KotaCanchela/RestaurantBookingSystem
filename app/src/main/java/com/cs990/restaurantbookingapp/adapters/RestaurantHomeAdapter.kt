package com.cs990.restaurantbookingapp

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cs990.restaurantbookingapp.models.RestaurantItem
import com.cs990.restaurantbookingapp.restaurantPage.RestaurantPageActivity
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.card_home_cuisine.view.*
import kotlinx.android.synthetic.main.card_home_restaurant.view.*

/**
 *  An Adapter Class that provides functionality for the Main Activity (or 'Home' page).
 * @author Group 1
 * @version 1.0
 */
class RestaurantHomeAdapter(
    val context: Context,
    val options: FirestoreRecyclerOptions<RestaurantItem>
) :
    FirestoreRecyclerAdapter<RestaurantItem, RestaurantHomeAdapter.RestaurantViewHolder>(options) {

    class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val restaurantNameText: TextView = itemView.findViewById(R.id.tv_restaurantName_home)
        val restaurantCuisineText: TextView = itemView.findViewById(R.id.tv_cuisine_home)
        val restaurantImageItem: ImageView = itemView.findViewById(R.id.iv_home_restaurant)
        val restaurantRatingBar: RatingBar = itemView.findViewById(R.id.rb_ratingBarHome)
        val restaurantPriceBar: RatingBar = itemView.findViewById(R.id.price_bar)
    }

    /**
     * onCreate method for this Class. Inflates the item views which is designed in xml layout file
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_home_restaurant,
                parent,
                false,
            ),
        )
    }

    /**
     * Binds each item in the ArrayList to a view. Called when RecyclerView needs a new
     * {@link ViewHolder} of the given type to represent an item.
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(
        holder: RestaurantViewHolder,
        position: Int,
        model: RestaurantItem
    ) {

//          Changed name to show image string (everything else is being reset to zero
        holder.restaurantNameText.tv_restaurantName_home.text = model.getName()
        holder.restaurantRatingBar.rb_ratingBarHome.rating = model.getRating()?.toFloat()!!
        holder.restaurantCuisineText.tv_cuisine_home.text = model.getCuisine()
        holder.restaurantRatingBar.rb_ratingBarHome.rating = model.getPrice()?.toFloat()!!

        val url = model.getRestaurantImage()
        Glide
            .with(holder.restaurantImageItem)
            .load(url)
            .into(holder.restaurantImageItem.iv_home_restaurant)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, RestaurantPageActivity::class.java)
            intent.putExtra("model", model)
            context.startActivity(intent) }
        }
}