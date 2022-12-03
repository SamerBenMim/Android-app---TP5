package com.gl4.tp5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var classSpinner : Spinner;
    lateinit var weather : TextView;
    lateinit var temp : TextView;
    lateinit var presser : TextView;
    lateinit var humidity : TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        val  WeatherView = WeatherViewModel()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        classSpinner = findViewById(R.id.spinner)
        weather = findViewById(R.id.textView2)
        presser = findViewById(R.id.textView4)
        temp = findViewById(R.id.textView)
        humidity = findViewById(R.id.textView3)

        ArrayAdapter.createFromResource(
            this,
            R.array.weather_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            classSpinner.adapter = adapter
        }

        WeatherView.weather.observe(this, Observer { e : WeatherResponse ->
            if(e != null){
                Log.d("sddddddddddddddddsss",e.toString())
                weather.text =  e.weather[0].description.toString() ;
                presser.text =  "pressure :" + e.main.pressure.toString() ;

                temp.text =     e.main.temp.toString()+" °C";
                humidity.text = "hum :" +  e.main.humidity.toString();

            }

        } )
    }
}