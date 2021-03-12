package com.cs990.restaurantbookingapp.Adapters

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.R
import com.cs990.restaurantbookingapp.RestaurantDetailsActivity
import com.cs990.restaurantbookingapp.models.RestaurantItem
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.card_restaurant.view.*
import java.net.URL
import kotlin.collections.ArrayList


class RestaurantItemAdapter(val context: Context, val options: FirestoreRecyclerOptions<RestaurantItem>) :
    FirestoreRecyclerAdapter<RestaurantItem, RestaurantItemAdapter.RestaurantViewHolder>(options) {

    private var onClickListener: OnClickListener? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_restaurant,
                parent,
                false,
            ),
        )
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int, model: RestaurantItem) {


//        if(holder is RestaurantViewHolder) {
            holder.restaurantNameText.tv_restaurantName.text = model.getRestaurantName()
            holder.restaurantDistanceText.tv_distance.text = model.getRestaurantGeohash()
            holder.restaurantRatingBar.rb_ratingBar.numStars = model.getRestaurantRating()

            var url: URL = URL(model.getRestaurantImage())
            var bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            holder.restaurantImageItem.iv_restaurantImage.setImageBitmap(bmp)



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

    /**
     * This is to get the onClickListener to keep hold of the item
     */
    interface OnClickListener{
        fun onClick(position: Int, model: RestaurantItem)
    }

    class RestaurantViewHolder(view:View): RecyclerView.ViewHolder(view){
        val restaurantNameText: TextView = itemView.findViewById(R.id.tv_restaurantName)
        val restaurantImageItem: ImageView = itemView.findViewById(R.id.iv_restaurantImage)
        val restaurantRatingBar: RatingBar = itemView.findViewById(R.id.rb_ratingBar)
        val restaurantDistanceText: TextView = itemView.findViewById(R.id.tv_distance)
    }


/*
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    restaurantFilterList = restaurantList

                } else {
                    val resultList = ArrayList<String>()


                    for (row in restaurantList) {
                        if (row.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)

                        }
                    }
                    restaurantFilterList = resultList

                }
                val filterResults = FilterResults()
                filterResults.values = restaurantFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                restaurantFilterList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }

 */

}

