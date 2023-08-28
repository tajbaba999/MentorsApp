package com.example.mentorsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mentorsapp.databinding.ActivityMainBinding
import com.example.mentorsapp.databinding.ActivityRegestrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = Firebase.auth.currentUser
//        if (user != null) {
//            binding.email.text = user.email.toString()
//        }

//        val url = ""
//        val  imgeViewproile = binding.imageview
//        Glide.with(this).load(url).circleCrop().fitCenter().into(imgeViewproile)
        //api calls


        val retrofit = RetrofitInstance.createRetrofitInstance()
        val apiService = retrofit.create(MentorService::class.java)


        val email = "p.madhavi@cvr.ac.in"
        val call: Call<MentorDetails> = apiService.getMentorDetails(email)

        call.enqueue(object  : Callback<MentorDetails>{
            override fun onResponse(call: Call<MentorDetails>, response: Response<MentorDetails>) {
                            if(response.isSuccessful){
                                val mentor: MentorDetails? = response.body()

                                mentor?.let {
                                    binding.name.text = it.name
                                    binding.desg.text = it.desg
                                    binding.sec.text = it.sec
                                    binding.phone.text= it.phono.toString()
                                }

                            }
                    }

            override fun onFailure(call: Call<MentorDetails>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error : ${t.localizedMessage}", Toast.LENGTH_LONG).show()
                }

            }
        )

    }
}