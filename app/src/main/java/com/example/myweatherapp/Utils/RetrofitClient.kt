package com.example.myweatherapp.Utils

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class RetrofitClient {

    var retrofit: Retrofit? = null

    fun getRetrofitClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }


}