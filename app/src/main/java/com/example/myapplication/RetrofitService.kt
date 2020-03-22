package com.example.myapplication

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {
    val endpoint :Endpoint by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        Retrofit.Builder().baseUrl("https://e80752e3.ngrok.io/").addConverterFactory(
            GsonConverterFactory.create(gson)).build().create(Endpoint::class.java)
    }
}