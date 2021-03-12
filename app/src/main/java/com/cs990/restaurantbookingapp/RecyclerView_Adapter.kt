package com.cs990.restaurantbookingapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_third.view.*
import kotlinx.android.synthetic.main.search_layout.view.*
import java.util.*
import kotlin.collections.ArrayList


class RecyclerView_Adapter(val context: Context, val restaurants: ArrayList<RestaurantItems>) :
    RecyclerView.Adapter<RecyclerView_Adapter.RestaurantHolder>() {

   // var restaurantFilterList = ArrayList<RestaurantItems>()

    lateinit var mcontext: Context

    class RestaurantHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantNameText: TextView = itemView.tv_restaurantName
        val restaurantImageItem: ImageView = itemView.iv_restaurantImage
        val restaurantRatingBar: RatingBar = itemView.rb_ratingBar
        val restaurantDistanceText: TextView = itemView.tv_distance
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantHolder {
        return RestaurantHolder(
            LayoutInflater.from(context).inflate(
                R.layout.search_layout, parent, false,
            ),
        )
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    override fun onBindViewHolder(holder: RestaurantHolder, position: Int) {
        val item = restaurants.get(index = position)

        holder.restaurantNameText.tv_restaurantName.setTextColor(Color.GRAY)
        holder.restaurantNameText.tv_restaurantName.text = item.getRestaurantName()
        holder.restaurantDistanceText.tv_distance.text = item.getRestaurantDistance()
        holder.restaurantRatingBar.rb_ratingBar.numStars = item.getRestaurantRating()
        holder.restaurantImageItem.iv_restaurantImage.setImageResource(item.getRestaurantImage())


        holder.itemView.setOnClickListener {

            val intent = Intent(context, RestaurantDetailsActivity::class.java)
            context.startActivity(intent)

        }
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

