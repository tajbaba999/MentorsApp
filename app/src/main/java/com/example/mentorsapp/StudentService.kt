package com.example.mentorsapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StudentService {
    @GET("students/{rollNumber}")
    fun getStudentDetails(@Path("rollNumber") rollNumber : String) : Call<StudentDetails>
}