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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MyViewModel : ViewModel() {

    private lateinit var weatherData: WeatherResponse
    private lateinit var CityweatherData: WeatherResponse

    private val data = MutableLiveData<WeatherResponse>()
    private val Citydata = MutableLiveData<WeatherResponse>()

    fun getCityData() : MutableLiveData<WeatherResponse>{
        return Citydata
    }

    fun getWeatherData(): MutableLiveData<WeatherResponse> {

        weatherData = WeatherResponse()
        loadWeather()
        return data
    }

    private fun loadWeather() {

        val newRetrofit : Retrofit? = RetrofitClient().getRetrofitClient()

        val service = newRetrofit?.create(WeatherService::class.java)

        val call : Call<WeatherResponse> = service!!.getCurrentWeatherData(lat, lon, AppId)

        if (call.isExecuted){
            call.cancel()
        }

        call.enqueue(object : Callback<WeatherResponse>{
            override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
                Log.d("Error", t.toString())
                call?.cancel()
            }

            override fun onResponse(call: Call<WeatherResponse>?, response: Response<WeatherResponse>?) {
                weatherData = response!!.body()
                Log.d("Response", weatherData.sys?.country)
                data.value = (weatherData)
                call?.cancel()
            }

        })

    }

    fun loadCityWeather(string: String) : MutableLiveData<WeatherResponse>{

        val retrofit : Retrofit? = RetrofitClient().getRetrofitClient()

        val service = retrofit?.create(WeatherService::class.java)

        val call : Call<WeatherResponse> = service!!.getCityWeatherData(string, AppId)

        if (call.isExecuted){
            call.cancel()
        }

        call.enqueue(object : Callback<WeatherResponse>{
            override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
                Log.d("Error", t.toString())
                call?.cancel()
            }

            override fun onResponse(call: Call<WeatherResponse>?, response: Response<WeatherResponse>?) {
                CityweatherData = response!!.body()
                Log.d("Response", CityweatherData.sys?.country)
                Citydata.value = (CityweatherData)
                call?.cancel()
            }
        })

        return Citydata
    }
}




