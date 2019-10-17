package com.example.myweatherapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(@Query("lat") lat : String, @Query("lon") lon : String, @Query("APPID") app_id : String): Call<WeatherResponse>

    @GET("data/2.5/weather?")
    fun getCityWeatherData(@Query("q") city : String, @Query("APPID") app_id : String): Call<WeatherResponse>
}