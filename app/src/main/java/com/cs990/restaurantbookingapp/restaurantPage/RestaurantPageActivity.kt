package com.cs990.restaurantbookingapp.restaurantPage

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs990.restaurantbookingapp.BaseActivity
import com.cs990.restaurantbookingapp.R
import com.cs990.restaurantbookingapp.adapters.RestaurantPageImageAdapter
import com.cs990.restaurantbookingapp.databinding.ActivityRestaurantPageBinding
import com.cs990.restaurantbookingapp.models.BookingItem
import com.cs990.restaurantbookingapp.models.RestaurantItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class RestaurantPageActivity : BaseActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityRestaurantPageBinding
    private lateinit var restaurantItem: RestaurantItem

    // Firebase
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var bookingRef: CollectionReference = db.collection("Users")
    private var currentUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!

    //adapter and recyclerview for images
    private lateinit var restaurantImageView: RecyclerView
    lateinit var imageAdapter: RestaurantPageImageAdapter

    // Booking dialog variables
    var numberGuests: String = ""
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRestaurantPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var intent: Intent = getIntent()
        restaurantItem = intent.getParcelableExtra<RestaurantItem>("model")!!

        setupUIViews()

        setupHorizontalScroll()

    }

    private fun setupUIViews() {
        restaurantImageView = binding.restaurantPageImageList

        val restaurantName = binding.restaurantName
        restaurantName.text = restaurantItem.getName()

        val restaurantRating = binding.ratingBar
        restaurantRating.rating = restaurantItem.getRating().toFloat()

        val restaurantLargeRating = binding.largeRatingBar
        restaurantLargeRating.rating = restaurantItem.getRating().toFloat()

        val restaurantPrice = binding.priceBar
        restaurantPrice.rating = restaurantItem.getPrice().toFloat()

        val restaurantCuisine = binding.restaurantCuisine
        restaurantCuisine.text = restaurantItem.getCuisine()


        val textView1 = binding.address1
        textView1.text = "12/28"
        val textView2 = binding.address2
        textView2.text = "Livingstone Tower"
        val textView3 = binding.address3
        textView3.text = "G1 G2G"
        val textView4 = binding.reviewCount
        textView4.text = "1,024 reviews"
        // val textView5 = binding.telNo
        // textView5.text = "tel: 12345678910"

        val cuisineDescription = binding.tvCuisine
        cuisineDescription.text = restaurantItem.getCuisine()

        //val priceDescription = binding.tvPrice
        //priceDescription.text = restaurantItem.getPrice().toString()
        setPriceDescription()

        val bookButton = binding.bookButton

        bookButton.setOnClickListener() {

            numberGuests = ""

            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.booking_dialog, null)
            var editText = dialogLayout.findViewById<EditText>(R.id.guest_num)

            with(builder) {
                setTitle("Select the number of guests")
                setPositiveButton("Ok") { dialog, which ->
                    numberGuests = editText.text.toString()
                    startDateTime()
                }
                setNegativeButton("Cancel") { dialog, which ->
                    Log.d("restaurant page", "Cancel booking dialog")
                }
                setView(dialogLayout)
                show()
            }


        }
    }


    private fun setPriceDescription() {
        val priceDescription = binding.tvPrice
        var restaurantPrice = restaurantItem.getPrice().toInt()
        if ((restaurantPrice == 1) || (restaurantPrice == 2)) {
            priceDescription.text = "£25 and Under"
        } else if ((restaurantPrice == 3) || (restaurantPrice == 4)) {
            priceDescription.text = "£26 to 40"
        } else if (restaurantPrice == 5) {
            priceDescription.text = "£41 and over"
        } else {
            priceDescription.text = restaurantItem.getPrice().toString()
        }
    }

    private fun setupHorizontalScroll() {
        restaurantImageView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        imageAdapter = RestaurantPageImageAdapter(this, getItemsList())
        restaurantImageView.adapter = imageAdapter
    }

    private fun getItemsList(): ArrayList<Int> {
        var list = ArrayList<Int>()

        list.add(R.drawable.gourmet_food)
        list.add(R.drawable.full_traditional_scottish_breakfast_lauripatterson)
        list.add(R.drawable.italian)
        list.add(R.drawable.blur1)
        list.add(R.drawable.blur3)
        list.add(R.drawable.mirazur_world_2019_dish1_min)

        return list
    }

    private fun startDateTime() {
        getDateTimeCalendar()
        DatePickerDialog(this, this, year, month, day).show()
    }

    private fun getDateTimeCalendar() {
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = day
        savedMonth = month
        savedYear = year



        TimePickerDialog(this, this, hour, minute, true).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hour
        savedMinute = minute

        //Get the current user id


        //creating instance of bookingItem and writing to database
        var bookingItem: BookingItem = BookingItem(
            restaurantItem,
            numberGuests,
            day.toString(),
            month.toString(),
            year.toString(),
            hour.toString(),
            minute.toString()
        )
        bookingRef.document(currentUser.uid)
            .collection("Bookings")
            .add(bookingItem)


        //changing activity to display confirmation
        var timeString: String =
            "${restaurantItem.getName()} \n\n for $numberGuests people \n\n at $savedHour:$savedMinute on $savedDay/$savedMonth/$savedYear"
        val intent = Intent(applicationContext, BookingConfirmationActivity::class.java)
        intent.putExtra("bookingItem", bookingItem)
        intent.putExtra("timeString", timeString)
        applicationContext.startActivity(intent)

    }
}