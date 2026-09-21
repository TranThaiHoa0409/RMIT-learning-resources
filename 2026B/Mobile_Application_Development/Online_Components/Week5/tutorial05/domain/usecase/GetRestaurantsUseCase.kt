package com.example.tutorial05.domain.usecase

import com.example.tutorial05.domain.model.Restaurant
import com.example.tutorial05.domain.repository.RestaurantRepository

class GetRestaurantsUseCase(private val repository: RestaurantRepository) {
    suspend operator fun invoke(): Result<List<Restaurant>> = repository.getRestaurants()
}