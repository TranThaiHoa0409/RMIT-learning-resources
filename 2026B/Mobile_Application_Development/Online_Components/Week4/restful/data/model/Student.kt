package com.example.restful.data.model

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable

@Serializable
data class Student(val id: Int = 0,val name: String)

@Serializable
data class NewStudent(val name: String)