package com.cs990.restaurantbookingapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.cs990.restaurantbookingapp.R
import com.cs990.restaurantbookingapp.RestaurantPageActivity
import com.cs990.restaurantbookingapp.models.RestaurantItem
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.card_home_cuisine.view.*

class RestaurantCuisineHomeAdapter(
    val context: Context,
    val options: FirestoreRecyclerOptions<RestaurantItem>
) :
    FirestoreRecyclerAdapter<RestaurantItem, RestaurantCuisineHomeAdapter.RestaurantViewHolder>(
        options) {


    class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val restaurantNameText: TextView = itemView.findViewById(R.id.tv_restaurantCuisine_home)
        val restaurantImageItem: ImageView = itemView.findViewById(R.id.iv_home_cuisine)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {


        return RestaurantViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_home_cuisine,
                parent,
                false,
            ),
        )
    }



    override fun onBindViewHolder(
        holder: RestaurantViewHolder,
        position: Int,
        model: RestaurantItem
    ) {


        holder.restaurantNameText.tv_restaurantCuisine_home.text = model.getCuisine()

        val url = model.getRestaurantImage()
        Glide
            .with(holder.restaurantImageItem)
            .load(url)
            .into(holder.restaurantImageItem.iv_home_cuisine)


        holder.itemView.setOnClickListener {

            val intent = Intent(context, RestaurantPageActivity::class.java)
            intent.putExtra("model", model)
            context.startActivity(intent)
        }
    }
}

