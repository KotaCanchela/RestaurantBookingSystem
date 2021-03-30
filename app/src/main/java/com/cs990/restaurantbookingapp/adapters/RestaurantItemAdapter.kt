package com.cs990.restaurantbookingapp.adapters

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cs990.restaurantbookingapp.R
import com.cs990.restaurantbookingapp.models.BookingItem
import com.cs990.restaurantbookingapp.restaurantPage.RestaurantPageActivity
import com.cs990.restaurantbookingapp.models.RestaurantItem
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.card_restaurant.view.*
import java.util.concurrent.CompletableFuture.runAsync

/**
 *  An Adapter Class that provides functionality for Restaurant Items.
 * @author Group 1
 * @version 1.0
 */
class RestaurantItemAdapter(
    val context: Context,
    var options: FirestoreRecyclerOptions<RestaurantItem>
) :
    FirestoreRecyclerAdapter<RestaurantItem, RestaurantItemAdapter.RestaurantViewHolder>(options) {
    //Firestore
    private var currentUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var favouriteRef: CollectionReference = db.collection("Users").document(currentUser.uid)
            .collection("Favourites")
    var query: CollectionReference = db
            .collection("Users")
            .document(currentUser.uid)
            .collection("Favourites")

    /**
     * onCreate method for this Class. Inflates the item views which is designed in xml layout file
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_restaurant,
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

        holder.restaurantNameText.booking_restaurant_name.text = model.getName()
        holder.restaurantDistanceText.booking_distance.text = model.getGeoHash()
        holder.restaurantRatingBar.booking_ratingBar.rating = model.getRating()?.toFloat()!!

        val url = model.getRestaurantImage()
        Glide
            .with(holder.restaurantImageItem)
            .load(url)
            .into(holder.restaurantImageItem.booking_restaurantImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, RestaurantPageActivity::class.java)
            intent.putExtra("model", model)
            context.startActivity(intent)
        }
        //Favourites code
        var firstQuery: Query = favouriteRef.whereEqualTo("name", model.getName())
        var favouriteID: String? = null
        firstQuery.get().addOnCompleteListener {
            if (it.result?.isEmpty == false) {

                for (document in it.result!!) {
                    favouriteID = document.id
                }
                holder.restaurantIsFavourite.isChecked = true
            } else {

                holder.restaurantIsFavourite.isChecked = false
            }
        }
        holder.restaurantIsFavourite.setOnClickListener{
            //If the restaurant is already a favourite
            if(holder.restaurantIsFavourite.isChecked) {
                /** this block will check if the query returns anything
                 if it returns something, then it will delete that from the db and uncheck box
                 if it returns nothing, then it will add the model to the db and check the box **/
                firstQuery.get().addOnCompleteListener {
                    if (it.result?.isEmpty == false) {
                        for (document in it.result!!) {
                            favouriteID = document.id
                        }
                        holder.restaurantIsFavourite.isChecked = true
                    } else {
                        holder.restaurantIsFavourite.isChecked = false
                    }
                }
                Toast.makeText(context, "Added to favourites", Toast.LENGTH_SHORT).show()
                favouriteRef.add(model)
                holder.restaurantIsFavourite.isChecked = true
            } else { //If the restaurant is not yet a favourite
                firstQuery.get().addOnCompleteListener {
                    if (it.result?.isEmpty == false) {
                        for (document in it.result!!) {
                            favouriteID = document.id
                        }
                        holder.restaurantIsFavourite.isChecked = true
                    } else {
                        holder.restaurantIsFavourite.isChecked = false
                    }
                }
                Toast.makeText(context, "Removed from favourites", Toast.LENGTH_SHORT).show()
                favouriteRef.document(favouriteID!!).delete()
                holder.restaurantIsFavourite.isChecked = false

            }
        }
        runAsync {
            runCatching {
                val url = model.getRestaurantImage()
                Glide
                    .with(holder.restaurantImageItem)
                    .load(url)
                    .into(holder.restaurantImageItem.booking_restaurantImage)
            }
        }
    }

    /**
     * Sets options based on the firestore recycler.
     */
    fun setItems(options: FirestoreRecyclerOptions<RestaurantItem>){
        this.options = options
        super.onDataChanged()
    }

    /**
     * Inner class that describes an item view
     */
    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantIsFavourite: CheckBox = itemView.findViewById(R.id.btn_favourite)
        val restaurantNameText: TextView = itemView.findViewById(R.id.booking_restaurant_name)
        val restaurantImageItem: ImageView = itemView.findViewById(R.id.booking_restaurantImage)
        val restaurantRatingBar: RatingBar = itemView.findViewById(R.id.booking_ratingBar)
        val restaurantDistanceText: TextView = itemView.findViewById(R.id.booking_distance)
    }
}