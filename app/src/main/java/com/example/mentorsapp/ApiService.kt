package com.example.mentorsapp

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService  {
    @GET("mentors/{mailid}")
    fun getMentorDetails(@Path("mailid") maildAdress : String) : retrofit2.Call<MentorDetails>
}