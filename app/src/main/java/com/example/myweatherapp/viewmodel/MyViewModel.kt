package com.example.myweatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.model.WeatherResponse
import com.example.myweatherapp.model.WeatherService
import com.example.myweatherapp.utils.AppId
import com.example.myweatherapp.utils.RetrofitClient
import com.example.myweatherapp.utils.lat
import com.example.myweatherapp.utils.lon
import retrofit2.Callback
import retrofit2.Retrofit


class MyViewModel : ViewModel() {

    private lateinit var weatherData: WeatherResponse

    private val data = MutableLiveData<WeatherResponse>()

    fun getWeatherData(): MutableLiveData<WeatherResponse> {

        weatherData = WeatherResponse()
        loadWeather()
        return data
    }

    private fun loadWeather() {

        val newRetrofit : Retrofit? = RetrofitClient().getRetrofitClient()

        val service = newRetrofit?.create(WeatherService::class.java)

        val call : retrofit2.Call<WeatherResponse> = service!!.getCurrentWeatherData(lat, lon, AppId)

        if (call.isExecuted){
            call.cancel()
        }

        call.enqueue(object : Callback<WeatherResponse>{
            override fun onFailure(call: retrofit2.Call<WeatherResponse>?, t: Throwable?) {
                Log.d("Error", t.toString())
            }

            override fun onResponse(call: retrofit2.Call<WeatherResponse>?, response: retrofit2.Response<WeatherResponse>?) {
                weatherData = response!!.body()
                Log.d("Response", weatherData.sys?.country)
                data.value = (weatherData)
                call?.cancel()
            }

        })

    }
}




