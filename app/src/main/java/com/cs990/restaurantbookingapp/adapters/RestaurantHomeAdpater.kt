package com.cs990.restaurantbookingapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.models.RestaurantItem
import com.cs990.restaurantbookingapp.models.RestaurantItems
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.card_home_restaurant.view.*
import kotlinx.android.synthetic.main.card_restaurant.view.*
import kotlinx.android.synthetic.main.card_restaurant.view.tv_restaurantName


class RestaurantHomeAdapter(val context: Context, val options: ArrayList<RestaurantItems>) :
    RecyclerView.Adapter<RestaurantHomeAdapter.RestaurantViewHolder>() {



    class RestaurantViewHolder(view:View): RecyclerView.ViewHolder(view){
        val restaurantNameText: TextView = itemView.findViewById(R.id.tv_restaurantName_home)
        val restaurantImageItem: ImageView = itemView.findViewById(R.id.iv_home_restaurant)
        val restaurantRatingBar: RatingBar = itemView.findViewById(R.id.rb_ratingBarHome)
       // val restaurantDistanceText: TextView = itemView.findViewById(R.id.tv_distance)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {


        return RestaurantViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_home_restaurant,
                parent,
                false,
            ),
        )
    }


    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val item = options.get(index = position)


//          Changed name to show image string (everything else is being reset to zero
        holder.restaurantNameText.tv_restaurantName_home.text = item.getRestaurantName()
       // holder.restaurantDistanceText.tv_distance.text = model.getGeohash()
       holder.restaurantRatingBar.rb_ratingBarHome.rating = item.getRestaurantRating()?.toFloat()!!

        // Trying to set image from database but strict mode preventing internet calls
        /*
           var url: URL = URL(model.getRestaurantImage())
           var bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())

         */
            holder.restaurantImageItem.iv_home_restaurant.setImageResource(item.getRestaurantImage())





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







