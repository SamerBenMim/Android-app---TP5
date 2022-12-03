package com.example.tp4_android

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp4_android.weatherforecast.WeatherForecast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(city: String): ViewModel() {
    private val weatherMutable = MutableLiveData<WeatherResponse>();
    val weather : LiveData<WeatherResponse> = weatherMutable;
    private val allWeathersMutable = MutableLiveData<WeatherForecast>();
    val allWeathers : LiveData<WeatherForecast> = allWeathersMutable;
    var city: String = "Tunis";
    init {
        getWeather(city);
    }

    fun searchByCity(city: String){
        getWeather(city);
    }

    fun searchForecast(city: String){
        getAllWeather(city);
    }

    private fun getWeather(city: String = "Tunis") {
        RetrofitHelper.retrofitService.getWeather(city).enqueue(object: Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ){
                if(response.isSuccessful)
                    weatherMutable.value= response.body() as WeatherResponse
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }

    private fun getAllWeather(city: String= "Tunis"){
        RetrofitHelper.retrofitService.getAllWeathers(city).enqueue(object: Callback<WeatherForecast>{
            override fun onResponse(
                call: Call<WeatherForecast>,
                response: Response<WeatherForecast>
            ){
                if(response.isSuccessful)
                    allWeathersMutable.value= response.body() as WeatherForecast
            }

            override fun onFailure(call: Call<WeatherForecast>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }
}