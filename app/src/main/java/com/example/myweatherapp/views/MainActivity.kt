package com.example.myweatherapp.views

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myweatherapp.*
import com.example.myweatherapp.utils.*
import com.example.myweatherapp.viewmodel.MyViewModel
import com.example.myweatherapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {(ViewModelProviders.of(this).get(MyViewModel::class.java))}

    private lateinit var sharedPreference : SharedPreferences

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        weatherIcon.visibility = View.GONE

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        viewModel.getWeatherData().removeObservers(this)

        sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)

        setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        bindArray(weatherDesc, viewModel)
        bindTemp(temperature, viewModel)
        bindmaxTemp(maxTempValue, viewModel)
        bindminTemp(minTempValue, viewModel)
        bindWeatherIcon(weatherIcon, viewModel)
        bindTime(lastUpdated, "Updated @ ")
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

                bindArray(weatherDesc, viewModel)
                bindTemp(temperature, viewModel)
                bindmaxTemp(maxTempValue, viewModel)
                bindminTemp(minTempValue, viewModel)
                bindWeatherIcon(weatherIcon, viewModel)
                bindTime(lastUpdated, "Updated @ ")

            })
        }

        viewModel.getWeatherData().removeObservers(this)

        Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show()

        return super.onOptionsItemSelected(item)
    }

}
