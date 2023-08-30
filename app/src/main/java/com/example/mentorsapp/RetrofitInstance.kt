package com.example.mentorsapp

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object {
        private const val BASE_URL = "https://mentor-student-umum.onrender.com/"

        fun createRetrofitInstance(): Retrofit {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // Adjust this timeout value
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient) // Set the custom OkHttpClient with timeouts
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}