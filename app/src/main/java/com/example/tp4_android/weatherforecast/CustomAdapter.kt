package com.example.tp4_android.weatherforecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4_android.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class CustomAdapter(private val mList: List<WeatherListElement>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val t = mList[position]
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val netDate = Date(t.dt.toLong() * 1000);
        holder.temp.setText((t.temp.day - 273.15).roundToInt().toString() + "°C")
        holder.day.setText(sdf.format(netDate))
        holder.humidity.setText("Humidity " + t.humidity.toString())
        holder.pressure.setText("Pressure " + t.pressure.toString())

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val temp: TextView = itemView.findViewById(R.id.textView6)
        val day: TextView = itemView.findViewById(R.id.textView8)
        val humidity: TextView = itemView.findViewById(R.id.textView7)
        val pressure: TextView = itemView.findViewById(R.id.textView9)
    }
}