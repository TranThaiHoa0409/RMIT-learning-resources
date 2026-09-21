package com.example.tutorial05.data.network

import com.example.tutorial05.data.network.RestaurantApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3004/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: RestaurantApi = retrofit.create(RestaurantApi::class.java)
}