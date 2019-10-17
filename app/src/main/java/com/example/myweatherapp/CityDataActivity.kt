package com.example.myweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myweatherapp.model.WeatherResponse
import com.example.myweatherapp.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.activity_city_data.*

class CityDataActivity : AppCompatActivity() {

    private val viewModel by lazy {(ViewModelProviders.of(this).get(MyViewModel::class.java))}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_data)



        viewModel.loadCityWeather("Lahore").observe(this, Observer {

            val list = ArrayList<WeatherResponse>()
            list.add(it)
            city_recyclerView.layoutManager = LinearLayoutManager(this)
            city_recyclerView.adapter = CityAdapter(list)
            Toast.makeText(this, list[0].name, Toast.LENGTH_LONG).show()
            viewModel.loadCityWeather("Lahore").removeObservers(this)
        })
    }
}
