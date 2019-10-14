package com.example.myweatherapp.ViewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myweatherapp.Model.Weather
import com.example.myweatherapp.Model.WeatherResponse
import com.example.myweatherapp.Model.WeatherService
import okhttp3.Call
import okhttp3.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.myweatherapp.Utils.*


class MyViewModel(application: Application) : AndroidViewModel(application) {

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
                data.postValue(weatherData)
                call?.cancel()
            }

        })

        /*val call = service.getCurrentWeatherData(lat, lon, AppId).enqueue(object :
            Callback<MutableLiveData<WeatherResponse>>{
            override fun onFailure(call: retrofit2.Call<MutableLiveData<WeatherResponse>>?, t: Throwable?) {
                Log.d("Error", t.toString())
            }

            override fun onResponse(call: retrofit2.Call<MutableLiveData<WeatherResponse>>?, response: retrofit2.Response<MutableLiveData<WeatherResponse>>?) {
                if (response?.isSuccessful!!){
                    weatherData = response.body()
                    Log.d("Response", weatherData.value?.sys?.country)
                }
            }

        })*/
    }
}




