package com.example.tp4_android.weatherforecast

data class WeatherForecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherListElement>,
    val message: Double
)