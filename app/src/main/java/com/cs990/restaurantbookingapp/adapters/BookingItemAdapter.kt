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
import com.cs990.restaurantbookingapp.restaurantPage.BookingConfirmationActivity
import com.cs990.restaurantbookingapp.restaurantPage.RestaurantPageActivity
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.card_booking.view.*
import kotlinx.android.synthetic.main.card_restaurant.view.booking_distance
import kotlinx.android.synthetic.main.card_restaurant.view.booking_ratingBar
import kotlinx.android.synthetic.main.card_restaurant.view.booking_restaurantImage
import kotlinx.android.synthetic.main.card_restaurant.view.booking_restaurant_name
import java.util.concurrent.CompletableFuture

/**
 *  An Adapter Class that provides functionality for Booking Items.
 * @author Group 1
 * @version 1.0
 */
class BookingItemAdapter(
    val context: Context,
    val options: FirestoreRecyclerOptions<BookingItem>): FirestoreRecyclerAdapter<BookingItem, BookingItemAdapter.BookingViewHolder>(options) {

    /**
     * onCreateViewHolder for this Class. Inflates the item views which is designed in xml
     * layout file
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        return BookingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_booking,
                parent,
                false,
            ),
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
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: BookingViewHolder, position: Int, model: BookingItem) {
        holder.restaurantNameText.booking_restaurant_name.text = model.getRestaurantItem().getName()
        holder.restaurantDistanceText.booking_distance.text = model.getRestaurantItem().getGeoHash()
        holder.restaurantRatingBar.booking_ratingBar.rating = model.getRestaurantItem().getRating()?.toFloat()!!
        holder.bookingDate.booking_date.text = "${model.getDay()}/${model.getMonth()}/${model.getYear()}"
        holder.bookingTime.booking_time.text = "${model.getHour()}:${model.getMinute()}"
        holder.numberGuests.number_guests.text = model.getGuestNumber()

        val url = model.getRestaurantItem().getRestaurantImage()
        Glide
            .with(holder.restaurantImageItem)
            .load(url)
            .into(holder.restaurantImageItem.booking_restaurantImage)

        holder.itemView.setOnClickListener {
            var timeString: String = "${model.getRestaurantItem().getName()} \n\n for ${model.getGuestNumber()} people \n\n" +
                    " at ${model.getHour()}:${model.getMinute()} on ${model.getDay()}/${model.getMonth()}/${model.getYear()}"
            val intent = Intent(context, BookingConfirmationActivity::class.java)
            intent.putExtra("timeString", timeString)
            intent.putExtra("bookingItem", model)
            context.startActivity(intent)
        }

        CompletableFuture.runAsync {
            runCatching {
                val url = model.getRestaurantItem().getRestaurantImage()
                Glide
                    .with(holder.restaurantImageItem)
                    .load(url)
                    .into(holder.restaurantImageItem.booking_restaurantImage)
            }
        }
    }

    /**
     * Inner class that describes an item view
     */
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