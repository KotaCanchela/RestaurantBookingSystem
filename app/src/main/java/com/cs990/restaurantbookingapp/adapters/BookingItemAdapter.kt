package com.cs990.restaurantbookingapp.adapters

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cs990.restaurantbookingapp.R
import com.cs990.restaurantbookingapp.models.BookingItem
import com.cs990.restaurantbookingapp.restaurantPage.RestaurantPageActivity
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.card_booking.view.*
import kotlinx.android.synthetic.main.card_restaurant.view.*
import kotlinx.android.synthetic.main.card_restaurant.view.booking_distance
import kotlinx.android.synthetic.main.card_restaurant.view.booking_ratingBar
import kotlinx.android.synthetic.main.card_restaurant.view.booking_restaurantImage
import kotlinx.android.synthetic.main.card_restaurant.view.booking_restaurant_name
import java.util.concurrent.CompletableFuture

class BookingItemAdapter(
    val context: Context,
    val options: FirestoreRecyclerOptions<BookingItem>): FirestoreRecyclerAdapter<BookingItem, BookingItemAdapter.BookingViewHolder>(options) {








    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        return BookingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_booking,
                parent,
                false,
            ),
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: BookingViewHolder, position: Int, model: BookingItem) {
        holder.restaurantNameText.booking_restaurant_name.text = model.getRestaurant().getName()
        holder.restaurantDistanceText.booking_distance.text = model.getRestaurant().getGeoHash()
        holder.restaurantRatingBar.booking_ratingBar.rating = model.getRestaurant().getRating()?.toFloat()!!
        holder.bookingDate.booking_date.text = "${model.getDay()}/${model.getMonth()}/${model.getYear()}"
        holder.bookingTime.booking_time.text = "${model.getHour()}:${model.getMinute()}"
        holder.numberGuests.number_guests.text = model.getGuestNumber()

        val url = model.getRestaurant().getRestaurantImage()
        Glide
            .with(holder.restaurantImageItem)
            .load(url)
            .into(holder.restaurantImageItem.booking_restaurantImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, RestaurantPageActivity::class.java)
            intent.putExtra("model", model.getRestaurant())

            context.startActivity(intent)

        }

        CompletableFuture.runAsync {
            runCatching {
                val url = model.getRestaurant().getRestaurantImage()
                Glide
                    .with(holder.restaurantImageItem)
                    .load(url)
                    .into(holder.restaurantImageItem.booking_restaurantImage)

            }
        }
    }

    inner class BookingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantNameText: TextView = itemView.findViewById(R.id.booking_restaurant_name)
        val restaurantImageItem: ImageView = itemView.findViewById(R.id.booking_restaurantImage)
        val restaurantRatingBar: RatingBar = itemView.findViewById(R.id.booking_ratingBar)
        val restaurantDistanceText: TextView = itemView.findViewById(R.id.booking_distance)
        val bookingTime: TextView = itemView.findViewById(R.id.booking_time)
        val bookingDate: TextView = itemView.findViewById(R.id.booking_date)
        val numberGuests: TextView = itemView.findViewById(R.id.number_guests)

    }
}