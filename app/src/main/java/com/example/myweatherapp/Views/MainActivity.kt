package com.example.myweatherapp.Views

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myweatherapp.R
import com.example.myweatherapp.Utils.*
import com.example.myweatherapp.viewmodel.MyViewModel
import com.example.myweatherapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {(ViewModelProviders.of(this).get(MyViewModel::class.java))}

    private lateinit var sharedPreference : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    private lateinit var city : String
    private lateinit var temp : String
    private lateinit var desc : String
    private lateinit var minTemp : String
    private lateinit var maxTemp : String
    private lateinit var pressure : String
    private lateinit var icon : String
    private lateinit var fetchtime : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.getWeatherData().removeObservers(this)

        sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sharedPreference.edit()

        setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        cityName.text = sharedPreference.getString(CITY_NAME, "")
        temperature.text = sharedPreference.getString(TEMP, "")
        temperature.append("\u2103")

        Glide.with(this).load("http://openweathermap.org/img/w/" + sharedPreference.getString(ICON, "") + ".png")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(weatherIcon)

        weatherDesc.text = sharedPreference.getString(DESC, "")

        minTempValue.text = sharedPreference.getString(MIN_TEMP, "")
        minTempValue.append("\u2103")

        maxTempValue.text = sharedPreference.getString(MAX_TEMP, "")
        maxTempValue.append("\u2103")

        pressureValue.text = sharedPreference.getString(PRESSURE, "")

        lastUpdated.text = UPDATE + sharedPreference.getString(LAST_UPDATED, "")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId

        if (id == R.id.action_search){

            viewModel.getWeatherData().observe(this, Observer {
                city = viewModel.getWeatherData().value?.name.toString()
                cityName.text = city
                temp = (viewModel.getWeatherData().value?.main?.temp?.let { it1 ->
                    Math.toIntExact(
                        it1.roundToLong()
                    ).minus(273)
                }).toString()
                temperature.text = temp
                temperature.append("\u2103" )

                icon = viewModel.getWeatherData().value?.weather?.get(viewModel.getWeatherData().value?.weather?.size!!-1)?.icon.toString()

                Glide.with(this).load("http://openweathermap.org/img/w/" + icon + ".png")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(weatherIcon)

                desc = viewModel.getWeatherData().value?.weather?.get(0)?.description.toString()

                weatherDesc.text = desc

                minTemp = (viewModel.getWeatherData().value?.main?.temp_min?.let { it1 ->
                    Math.toIntExact(
                        it1.roundToLong()
                    ).minus(273)
                }).toString()

                minTempValue.text = minTemp
                minTempValue.append("\u2103")

                maxTemp = (viewModel.getWeatherData().value?.main?.temp_max?.let { it1 ->
                    Math.toIntExact(
                        it1.roundToLong()
                    ).minus(273)
                }).toString()

                maxTempValue.text = maxTemp
                maxTempValue.append("\u2103")

                pressure = viewModel.getWeatherData().value?.main?.pressure.toString()

                pressureValue.text = pressure

                fetchtime = DateFormat.getTimeInstance(DateFormat.SHORT).format(System.currentTimeMillis())

                lastUpdated.text = UPDATE + fetchtime

                editor.putString(CITY_NAME, city)
                editor.putString(TEMP, temp)

                editor.putString(ICON, icon)
                editor.putString(DESC, desc)
                editor.putString(MIN_TEMP, minTemp)
                editor.putString(MAX_TEMP, maxTemp)
                editor.putString(PRESSURE, pressure)
                editor.putString(LAST_UPDATED, fetchtime)
                editor.apply()
            })
        }

        viewModel.getWeatherData().removeObservers(this)

        return super.onOptionsItemSelected(item)
    }

}
