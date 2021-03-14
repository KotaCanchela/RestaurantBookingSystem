package com.cs990.restaurantbookingapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.R
import com.cs990.restaurantbookingapp.RestaurantDetailsActivity
import com.cs990.restaurantbookingapp.RestaurantHomeAdapter
import com.cs990.restaurantbookingapp.models.RestaurantItems
import kotlinx.android.synthetic.main.card_home_cuisine.view.*
import kotlinx.android.synthetic.main.card_home_restaurant.view.*
import kotlinx.android.synthetic.main.card_home_restaurant.view.iv_home_restaurant

class RestaurantCuisineHomeAdapter(val context: Context, val options: ArrayList<RestaurantItems>) :
    RecyclerView.Adapter<RestaurantCuisineHomeAdapter.RestaurantViewHolder>() {



    class RestaurantViewHolder(view: View): RecyclerView.ViewHolder(view){
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


    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val item = options.get(index = position)


//          Changed name to show image string (everything else is being reset to zero
        holder.restaurantNameText.tv_restaurantCuisine_home.text = item.getRestaurantName()
        // holder.restaurantDistanceText.tv_distance.text = model.getGeohash()

        // Trying to set image from database but strict mode preventing internet calls
        /*
           var url: URL = URL(model.getRestaurantImage())
           var bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())

         */
        holder.restaurantImageItem.iv_home_cuisine.setImageResource(item.getRestaurantImage())





        holder.itemView.setOnClickListener {
            /*
           if(onClickListener!= null){
               onClickListener!!.onClick(position, model)
           }

             */
            val intent = Intent(context, RestaurantDetailsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }
}

