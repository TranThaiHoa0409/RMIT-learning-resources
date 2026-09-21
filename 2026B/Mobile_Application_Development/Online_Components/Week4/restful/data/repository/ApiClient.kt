package com.example.restful.data.repository

import com.example.restful.data.network.StudentApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object ApiClient {
    val studentApi: StudentApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3004/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(StudentApi::class.java)
    }
}