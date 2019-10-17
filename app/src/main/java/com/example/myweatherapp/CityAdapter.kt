package com.example.myweatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.model.WeatherResponse

class CityAdapter(private val mutableLiveData: ArrayList<WeatherResponse>) : RecyclerView.Adapter<CityAdapter.CityHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.city_rows, parent, false)
        return CityHolder(binding)
    }

    override fun getItemCount(): Int {
        return mutableLiveData.size
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bindData(mutableLiveData[position])
    }

    class CityHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data : Any?){
            binding.setVariable(BR.viewmodel, data)
            binding.executePendingBindings()
        }
    }

}