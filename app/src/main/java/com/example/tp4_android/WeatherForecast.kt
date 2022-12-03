package com.example.tp4_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4_android.weatherforecast.CustomAdapter
import com.example.tp4_android.weatherforecast.WeatherForecast
import com.example.tp4_android.weatherforecast.WeatherListElement
import kotlin.math.roundToInt

class WeatherForecast : AppCompatActivity() {
    lateinit var viewModel : WeatherViewModel;
    lateinit var weathers : WeatherForecast;
    lateinit var recyclerView : RecyclerView;
    lateinit var list : ArrayList<WeatherListElement>;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast)
        var city = intent.getStringExtra("city") as String;
        viewModel = WeatherViewModel(city);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.layoutManager = LinearLayoutManager(applicationContext);
        viewModel.searchForecast(city);
        list = ArrayList<WeatherListElement>()
        val adapter = CustomAdapter(list);
        recyclerView.adapter = adapter;
        viewModel.allWeathers.observe(this, Observer { t: WeatherForecast ->
            if(t != null) {
                Log.d("tag",t.toString());

                list = t.list as ArrayList<WeatherListElement>
                val adapter = CustomAdapter(list);
                recyclerView.adapter = adapter;
            }
        })
    }
}