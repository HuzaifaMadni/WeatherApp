package com.example.myweatherapp.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.myweatherapp.BR
import com.google.gson.annotations.SerializedName
import java.util.*


class WeatherResponse : BaseObservable() {

    @get:Bindable
    @SerializedName("coord")
    var coord: Coord? = null
    set(value) {
        field = value
        notifyPropertyChanged(BR.viewmodel)
    }

    @get:Bindable
    @SerializedName("sys")
    var sys: Sys? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.viewmodel)
        }

    @get:Bindable
    @SerializedName("weather")
    var weather = ArrayList<Weather>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.viewmodel)
        }

    @get:Bindable
    @SerializedName("main")
    var main: Main? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.viewmodel)
        }

    @get:Bindable
    @SerializedName("wind")
    var wind: Wind? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.viewmodel)
        }

    @get:Bindable
    @SerializedName("rain")
    var rain: Rain? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.viewmodel)
        }

    @get:Bindable
    @SerializedName("clouds")
    var clouds: Clouds? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.viewmodel)
        }

    @get:Bindable
    @SerializedName("dt")
    var dt: Float = 0.toFloat()
        set(value) {
            field = value
            notifyPropertyChanged(BR.viewmodel)
        }

    @get:Bindable
    @SerializedName("id")
    var id: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.viewmodel)
        }

    @get:Bindable
    @SerializedName("name")
    var name: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.viewmodel)
        }

    @get:Bindable
    @SerializedName("cod")
    var cod: Float = 0.toFloat()
        set(value) {
            field = value
            notifyPropertyChanged(BR.viewmodel)
        }

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


