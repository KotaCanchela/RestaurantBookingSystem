package com.cs990.restaurantbookingapp

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.RadioButton
import android.widget.SeekBar
import kotlinx.android.synthetic.main.dialog_filter.*
import kotlinx.android.synthetic.main.fragment_third.*

/**
 *  An Android Dialog Class that provides functionality for a filters screen. Defines a series
 *  of interactive views that can capture user search options
 * @author Group 1
 * @version 1.0
 */
class FilterDialog(context: Context) : Dialog(context){

    init {
        setCancelable(true)
    }
    //rating default of 0, if rating slider not enabled
    private var ratingSelected = 0
    //String list for the price NumberPicker
    private var dolValues= arrayOf("£", "££", "£££", "££££", "£££££")

    //Button setup
    private lateinit var seekBar: SeekBar
    private lateinit var ratingButton: RadioButton
    private lateinit var ratingRadioButton: RadioButton
    private lateinit var dietRadioButton: RadioButton

    // Boolean RadioButton trackers
    private var ratingButtonBoolean: Boolean = false
    private var dietaryButtonBoolean: Boolean = false

    /**
     * onCreate method for this Dialog. Triggers methods that instantiate interactive views
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_filter)

        setupBindings()
        setupUI()
    }

    /**
     * Sets initial values and states of radio buttons, number picker and seek bar on the filters
     * page. Also replaces number picker numeral values with Strings.
     */
    fun setupBindings(){
        ratingRadioButton = findViewById(R.id.rating_radio_button)
        dietRadioButton = findViewById(R.id.spec_diet_radio_button)

        //set bounds for the "number of people" number picker
        val np = findViewById<View>(R.id.num_people_picker) as NumberPicker
        np.maxValue = 20
        np.minValue = 1
        np.value = 1

        //disable the SeekBar by default
        seekBar = findViewById<View>(R.id.ratingSeekBar) as SeekBar
        seekBar.isEnabled = false
        seekBar.incrementProgressBy(1)
        seekBar.max = 4

        //set dietary radio buttons to disabled by default
        val r1 = findViewById<View>(R.id.vegan_button) as RadioButton
        r1.isEnabled = false
        val r2 = findViewById<View>(R.id.halal_button) as RadioButton
        r2.isEnabled = false
        val r3 = findViewById<View>(R.id.dairy_button) as RadioButton
        r3.isEnabled = false
        val r4 = findViewById<View>(R.id.gluten_button) as RadioButton
        r4.isEnabled = false
        val r5 = findViewById<View>(R.id.nut_button) as RadioButton
        r5.isEnabled = false

        //set price spinner to strings instead of ints
        val ds = findViewById<View>(R.id.price_picker) as NumberPicker
        ds.displayedValues = dolValues
        ds.maxValue = 4
        ds.minValue = 0
        ds.value = 0
    }

    /**
     * Configures click listeners for the rating and dietary requirements radio buttons that
     * enable/disable other radio button options. Also adds a change listener for the seek bard
     * and records the current value selected by the user.
     */
    fun setupUI(){
        ratingRadioButton.setOnClickListener(){
            ratingRadioButtonOnClick(ratingRadioButton)
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean)
            {
                //bounds are 0-4, so add 1 to get the final rating requested
                ratingSelected = progress + 1
            }
            override fun onStartTrackingTouch(seekBar: SeekBar)
            {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar)
            {
            }
        })

        dietRadioButton.setOnClickListener(){
            specDietRadioButtonOnClick(dietRadioButton)
        }
    }


    private fun ratingRadioButtonOnClick(view: View) {

        if(!ratingButtonBoolean) {
            val checked = ratingRadioButton.isChecked
            seekBar = findViewById<View>(R.id.ratingSeekBar) as SeekBar
            seekBar.isEnabled = checked
            ratingButtonBoolean = true
        } else {
            seekBar.isEnabled = false
            ratingRadioButton.isChecked = false
            ratingButtonBoolean = false
        }
    }

    /**
     * onClick() method for the "Special Dietary Needs" checkbox. Implemented as an onClick
     * rather than action listener due to lack of group. Makes the individual radio buttons
     * selectable when active
     * @param view
     */
    fun specDietRadioButtonOnClick(view: View)
    {

        if(!dietaryButtonBoolean) {
            val checked = (view as RadioButton).isChecked
            dietaryButtonBoolean = true
            val r1 = findViewById<View>(R.id.vegan_button) as RadioButton
            r1.isEnabled = false
            val r2 = findViewById<View>(R.id.halal_button) as RadioButton
            r2.isEnabled = false
            val r3 = findViewById<View>(R.id.dairy_button) as RadioButton
            r3.isEnabled = false
            val r4 = findViewById<View>(R.id.gluten_button) as RadioButton
            r4.isEnabled = false
            val r5 = findViewById<View>(R.id.nut_button) as RadioButton
            r5.isEnabled = false
            if (checked) {
                r1.isEnabled = true
                r2.isEnabled = true
                r3.isEnabled = true
                r4.isEnabled = true
                r5.isEnabled = true
            } else {
                r1.isEnabled = false
                r2.isEnabled = false
                r3.isEnabled = false
                r4.isEnabled = false
                r5.isEnabled = false
            }
        } else {
            dietRadioButton.isChecked = false
            dietaryButtonBoolean = false
            val r1 = findViewById<View>(R.id.vegan_button) as RadioButton
            r1.isChecked = false
            r1.isEnabled = false
            val r2 = findViewById<View>(R.id.halal_button) as RadioButton
            r2.isEnabled = false
            r2.isChecked = false
            val r3 = findViewById<View>(R.id.dairy_button) as RadioButton
            r3.isEnabled = false
            r3.isChecked = false
            val r4 = findViewById<View>(R.id.gluten_button) as RadioButton
            r4.isEnabled = false
            r4.isChecked = false
            val r5 = findViewById<View>(R.id.nut_button) as RadioButton
            r5.isEnabled = false
            r5.isChecked = false
        }
    }
}