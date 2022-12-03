package com.example.tp4_android

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val baseUrl ="https://api.openweathermap.org/data/2.5/"
    /**
     * The Retrofit object with Gson converter.
     */
    private val retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        // we need to add converter factory to
        // convert JSON object to Java object
        .build()
    /**
     * A public Api object that exposes the lazy-initialized Retrofit service
     */
    val retrofitService : WeatherAPI by lazy { retrofit.create(WeatherAPI::class.java) }

}