package com.example.tmldemoapp2

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("geopoints")
    fun getData(): Call<List<Geopoint>>
}