package com.example.tp4_android

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import java.net.URL
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    lateinit var spinner: Spinner;
    lateinit var WeatherView: WeatherViewModel;
    lateinit var textView: TextView;
    lateinit var textView2: TextView;
    lateinit var textView3: TextView;
    lateinit var textView4: TextView;
    lateinit var imageView: ImageView;
    lateinit var button: Button;
    var city: String = "Tunis";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        WeatherView = WeatherViewModel("Tunis");
        spinner = findViewById(R.id.spinner);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        imageView = findViewById(R.id.imageView2);
        button = findViewById(R.id.button);
        var adapter = ArrayAdapter.createFromResource(
            this,
            R.array.city,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                WeatherView.searchByCity(resources.getStringArray(R.array.city)[position]);
                city = resources.getStringArray(R.array.city)[position];
            }
        }
        button.setOnClickListener{
            val changePage = Intent(this, WeatherForecast::class.java);
            changePage.putExtra("city",city)
            startActivity(changePage);
        }
        WeatherView.weather.observe(this, Observer { t: WeatherResponse ->
            if(t != null) {
                textView.setText((t.main.temp - 273.15).roundToInt().toString() + "°C")
                textView2.setText(t.weather[0].description)
                textView3.setText("Humidity " + t.main.humidity.toString())
                textView4.setText("Pressure " + t.main.pressure.toString())
                Glide.with(this).load("https://openweathermap.org/img/wn/"+t.weather[0].icon+"@4x.png").into(imageView)
            }
        })
    }
}