package com.example.myweatherapp.Model

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.google.gson.annotations.SerializedName
import java.util.*

class WeatherResponse : BaseObservable() {

    @SerializedName("coord")
    var coord: Coord? = null
    @SerializedName("sys")
    var sys: Sys? = null
    @SerializedName("weather")
    var weather = ArrayList<Weather>()
    @SerializedName("main")
    var main: Main? = null
    @SerializedName("wind")
    var wind: Wind? = null
    @SerializedName("rain")
    var rain: Rain? = null
    @SerializedName("clouds")
    var clouds: Clouds? = null
    @SerializedName("dt")
    var dt: Float = 0.toFloat()
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String? = null
    @SerializedName("cod")
    var cod: Float = 0.toFloat()

    get() = field


}

class Weather : BaseObservable(){
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("main")
    var main: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("icon")
    var icon: String? = null


}

class Clouds : BaseObservable(){
    @SerializedName("all")
    var all: Float = 0.toFloat()
}

class Rain : BaseObservable(){
    @SerializedName("3h")
    var h3: Float = 0.toFloat()
}

class Wind : BaseObservable (){
    @SerializedName("speed")
    var speed: Float = 0.toFloat()
    @SerializedName("deg")
    var deg: Float = 0.toFloat()
}

class Main : BaseObservable(){
    @SerializedName("temp")
    var temp: Float = 0.toFloat()
    @SerializedName("humidity")
    var humidity: Float = 0.toFloat()
    @SerializedName("pressure")
    var pressure: Float = 0.toFloat()
    @SerializedName("temp_min")
    var temp_min: Float = 0.toFloat()
    @SerializedName("temp_max")
    var temp_max: Float = 0.toFloat()
}

class Sys : BaseObservable(){
    @SerializedName("country")
    var country: String? = null
    @SerializedName("sunrise")
    var sunrise: Long = 0
    @SerializedName("sunset")
    var sunset: Long = 0
}

class Coord : BaseObservable(){
    @SerializedName("lon")
    var lon: Float = 0.toFloat()
    @SerializedName("lat")
    var lat: Float = 0.toFloat()
}

