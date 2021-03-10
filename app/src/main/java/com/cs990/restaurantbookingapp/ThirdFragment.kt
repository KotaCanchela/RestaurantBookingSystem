package com.cs990.restaurantbookingapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.ListFragment
import com.cs990.restaurantbookingapp.databinding.FragmentThirdBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)



        profileListView = binding.profileListView

//        var listViewAdapter: ArrayAdapter<String> = ArrayAdapter(this.requireActivity(), android.R.layout.simple_list_item_1, profileMenuItems)
        profileListView.adapter = MyCustomAdapter(this.requireActivity())

        // Inflate the layout for this fragment

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ThirdFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThirdFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private class MyCustomAdapter(context: Context): BaseAdapter() {

        private val mContext: Context = context
        var profileMenuItems = arrayListOf("Saved Restaurants", "Bookings", "Requests", "Payment Details", "Your ratings", "Logout")


        override fun getCount(): Int {
            return profileMenuItems.size
        }

        override fun getItem(position: Int): Any {
            return "test string"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, viewgroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.row_profile, viewgroup, false)

            val positionTextView = rowMain.findViewById<TextView>(R.id.row_text)
            positionTextView.text = profileMenuItems[position]

            return rowMain
        }
    }

}