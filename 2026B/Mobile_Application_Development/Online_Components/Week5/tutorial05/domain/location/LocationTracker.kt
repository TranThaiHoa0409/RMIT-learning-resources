package com.example.tutorial05.domain.location

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface LocationTracker {
    fun getLocationUpdates(): Flow<LatLng>
}