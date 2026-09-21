package com.example.tutorial05.data.network

import com.example.tutorial05.data.model.RestaurantDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestaurantApi {
    @GET("restaurants")
    suspend fun getRestaurants(): List<RestaurantDto>

    @POST("restaurants")
    suspend fun addRestaurant(@Body restaurant: RestaurantDto): RestaurantDto
}