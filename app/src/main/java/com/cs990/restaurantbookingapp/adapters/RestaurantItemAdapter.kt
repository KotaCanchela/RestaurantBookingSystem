package com.cs990.restaurantbookingapp.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.R
import com.cs990.restaurantbookingapp.models.RestaurantItem
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.card_restaurant.view.*
import java.net.URL


class RestaurantItemAdapter(val context: Context, val options: FirestoreRecyclerOptions<RestaurantItem>) :
    FirestoreRecyclerAdapter<RestaurantItem, RestaurantItemAdapter.RestaurantViewHolder>(options) {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_restaurant,
                parent,
                false,
            ),
        )
    }


    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int, model: RestaurantItem) {


//          Changed name to show image string (everything else is being reset to zero
            holder.restaurantNameText.tv_restaurantName.text = model.getRestaurantImage()
            holder.restaurantDistanceText.tv_distance.text = model.getRestaurantGeohash()
            holder.restaurantRatingBar.rb_ratingBar.numStars = model.getRestaurantRating()!!

            // Trying to set image from database but strict mode preventing internet calls
//            var url: URL = URL(model.getRestaurantImage())
//            var bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//            holder.restaurantImageItem.iv_restaurantImage.setImageBitmap(bmp)



//            holder.itemView.setOnClickListener {
//
//                if(onClickListener!= null){
//                    onClickListener!!.onClick(position, model)
//                }
//                val intent = Intent(context, RestaurantDetailsActivity::class.java)
//                context.startActivity(intent)
//
//            }
//        }
    }


    class RestaurantViewHolder(view:View): RecyclerView.ViewHolder(view){
        val restaurantNameText: TextView = itemView.findViewById(R.id.tv_restaurantName)
        val restaurantImageItem: ImageView = itemView.findViewById(R.id.iv_restaurantImage)
        val restaurantRatingBar: RatingBar = itemView.findViewById(R.id.rb_ratingBar)
        val restaurantDistanceText: TextView = itemView.findViewById(R.id.tv_distance)
    }


}

