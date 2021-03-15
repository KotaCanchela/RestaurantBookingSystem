package com.cs990.restaurantbookingapp.adapters

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.R
import com.cs990.restaurantbookingapp.RestaurantPageActivity
import com.cs990.restaurantbookingapp.models.RestaurantItem
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.card_home_cuisine.view.*
import kotlinx.android.synthetic.main.card_home_restaurant.view.*
import java.net.URL
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletableFuture.runAsync as runAsync

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


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(
        holder: RestaurantViewHolder,
        position: Int,
        model: RestaurantItem
    ) {


//          Changed name to show image string (everything else is being reset to zero
        holder.restaurantNameText.tv_restaurantCuisine_home.text = model.getCuisine()
        // holder.restaurantDistanceText.tv_distance.text = model.getGeohash()

        // Trying to set image from database but strict mode preventing internet calls
        /*
           var url: URL = URL(model.getRestaurantImage())
           var bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())

         */
//        runAsync {
//            runCatching {
//                val bitmap = URL(model.getRestaurantImage()).openStream()
//                    .use { BitmapFactory.decodeStream(it) }
//                holder.restaurantImageItem.iv_home_cuisine.setImageBitmap(bitmap)
//            }
//        }

      //  holder.restaurantImageItem.iv_home_cuisine.setImageResource(item.getRestaurantImage())



        holder.itemView.setOnClickListener {

            val intent = Intent(context, RestaurantPageActivity::class.java)
            intent.putExtra("model", model)
            context.startActivity(intent)
        }
    }
}

