package com.example.tutorial05.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tutorial05.data.location.DefaultLocationTracker
import com.example.tutorial05.data.repository.RestaurantRepositoryImpl
import com.example.tutorial05.domain.usecase.AddRestaurantUseCase
import com.example.tutorial05.domain.usecase.GetRestaurantsUseCase

class MapViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            val repository = RestaurantRepositoryImpl()
            val locationTracker = DefaultLocationTracker(context)
            val getRestaurantsUseCase = GetRestaurantsUseCase(repository)
            val addRestaurantUseCase = AddRestaurantUseCase(repository)
            @Suppress("UNCHECKED_CAST")
            return MapViewModel(getRestaurantsUseCase, addRestaurantUseCase, locationTracker) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}