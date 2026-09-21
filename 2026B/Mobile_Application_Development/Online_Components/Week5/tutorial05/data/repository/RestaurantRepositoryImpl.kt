package com.example.tutorial05.data.repository

import com.example.tutorial05.data.model.toDomain
import com.example.tutorial05.data.model.toDto
import com.example.tutorial05.data.network.ApiClient
import com.example.tutorial05.domain.model.Restaurant
import com.example.tutorial05.domain.repository.RestaurantRepository

class RestaurantRepositoryImpl : RestaurantRepository {
    override suspend fun getRestaurants(): Result<List<Restaurant>> {
        return try {
            val response = ApiClient.api.getRestaurants()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addRestaurant(restaurant: Restaurant): Result<Unit> {
        return try {
            ApiClient.api.addRestaurant(restaurant.toDto())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}