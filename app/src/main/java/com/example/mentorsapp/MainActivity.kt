package com.example.mentorsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mentorsapp.databinding.ActivityMainBinding
import com.example.mentorsapp.databinding.ActivityRegestrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = Firebase.auth.currentUser
        if (user != null) {
            binding.mail.text = user.email.toString()
        }
    }
}