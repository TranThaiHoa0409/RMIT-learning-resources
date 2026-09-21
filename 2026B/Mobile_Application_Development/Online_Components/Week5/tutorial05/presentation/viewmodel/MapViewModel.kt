package com.example.tutorial05.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tutorial05.domain.model.Restaurant
import com.example.tutorial05.domain.location.LocationTracker
import com.example.tutorial05.domain.usecase.AddRestaurantUseCase
import com.example.tutorial05.domain.usecase.GetRestaurantsUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapViewModel(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val addRestaurantUseCase: AddRestaurantUseCase,
    private val locationTracker: LocationTracker
) : ViewModel() {

    val restaurants = mutableStateListOf<Restaurant>()
    val currentLocation = mutableStateOf<LatLng?>(null)

    init {
        loadRestaurants()
        startLocationUpdates()
    }

    fun loadRestaurants() {
        viewModelScope.launch(Dispatchers.IO) {
            getRestaurantsUseCase().onSuccess { data ->
                withContext(Dispatchers.Main) {
                    restaurants.clear()
                    restaurants.addAll(data)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun addRestaurant(latLng: LatLng, name: String) {
        val newRestaurant = Restaurant(name = name, lat = latLng.latitude, lng = latLng.longitude)
        viewModelScope.launch(Dispatchers.IO) {
            addRestaurantUseCase(newRestaurant).onSuccess {
                loadRestaurants()
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    private fun startLocationUpdates() {
        viewModelScope.launch {
            locationTracker.getLocationUpdates().collect { location ->
                currentLocation.value = location
            }
        }
    }
}