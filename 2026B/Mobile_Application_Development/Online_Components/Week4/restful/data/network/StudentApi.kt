package com.example.restful.data.network

import com.example.restful.data.model.NewStudent
import com.example.restful.data.model.Student
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface StudentApi {
    @GET("students")
    suspend fun getStudents(): List<Student>

    @POST("students")
    suspend fun addStudent(@Body student: NewStudent): Student

    @DELETE("students/{id}")
    suspend fun deleteStudent(@Path("id") id: Int): Response<Unit>

    @PUT("students/{id}")
    suspend fun updateStudent(@Path("id") id: Int, @Body student: Student): Response<Unit>
}
