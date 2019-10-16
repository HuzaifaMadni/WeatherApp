package com.example.myweatherapp.utils

import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myweatherapp.viewmodel.MyViewModel
import java.text.DateFormat
import kotlin.math.roundToLong

@BindingAdapter("bind:array")
fun bindArray(
    view: TextView,
    viewModel: MyViewModel
) {

    view.text = viewModel.getWeatherData().value?.weather?.get(0)?.description
}

@RequiresApi(Build.VERSION_CODES.N)
@BindingAdapter("bind:Temp")
fun bindTemp(
    view: TextView,
    viewModel: MyViewModel
) {
    view.text = (viewModel.getWeatherData().value?.main?.temp?.let { it1 ->
        Math.toIntExact(
            it1.roundToLong()
        ).minus(273)
    }).toString()
}

@RequiresApi(Build.VERSION_CODES.N)
@BindingAdapter("bind:minTemp")
fun bindminTemp(
    view: TextView,
    viewModel: MyViewModel
) {
    view.text = (viewModel.getWeatherData().value?.main?.temp_min?.let { it1 ->
        Math.toIntExact(
            it1.roundToLong()
        ).minus(273)
    }).toString()
}

@RequiresApi(Build.VERSION_CODES.N)
@BindingAdapter("bind:maxTemp")
fun bindmaxTemp(
    view: TextView,
    viewModel: MyViewModel
) {
    view.text = (viewModel.getWeatherData().value?.main?.temp_max?.let { it1 ->
        Math.toIntExact(
            it1.roundToLong()
        ).minus(273)
    }).toString()
}

@BindingAdapter("bind:weatherIcon")
fun bindWeatherIcon(
    view: ImageView,
    viewModel: MyViewModel
) {
    Glide.with(view.context).load("http://openweathermap.org/img/w/" + viewModel.getWeatherData().value?.weather?.get(0)?.icon.toString() + ".png")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(view)
    view.visibility = View.VISIBLE
}

@BindingAdapter("bind:updatedTime")
fun bindTime(view: TextView, string: String){
    view.text =  string + DateFormat.getTimeInstance(DateFormat.SHORT).format(System.currentTimeMillis())
}