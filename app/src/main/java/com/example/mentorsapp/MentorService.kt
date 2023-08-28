package com.example.mentorsapp


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MentorService {
    @GET("mentors/{email}")
    fun getMentorDetails(@Path("email") email : String) : Call<MentorDetails>
}