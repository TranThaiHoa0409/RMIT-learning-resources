package com.example.tutorial05.data.model

import com.example.tutorial05.domain.model.Restaurant

data class RestaurantDto(
    val id: Int? = null,
    val name: String,
    val lat: Double,
    val lng: Double
)

fun RestaurantDto.toDomain(): Restaurant {
    return Restaurant(
        id = id,
        name = name,
        lat = lat,
        lng = lng
    )
}

fun Restaurant.toDto(): RestaurantDto {
    return RestaurantDto(
        id = id,
        name = name,
        lat = lat,
        lng = lng
    )
}