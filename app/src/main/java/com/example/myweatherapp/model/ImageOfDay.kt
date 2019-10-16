package com.example.myweatherapp.model

data class ImageOfDay (

    private val date : String,
    private val explanation : String,
    private val hdurl : String,
    private val mediaType : String,
    private val serviceVersion : String,
    private val title : String,
    private val url : String
)
