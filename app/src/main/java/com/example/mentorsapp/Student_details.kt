package com.example.mentorsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mentorsapp.databinding.ActivityStudentDetailsBinding

class Student_details : AppCompatActivity() {
    lateinit var binding: ActivityStudentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val rollNumber = intent.getStringExtra("rollNumber")
        binding.rollNumber.text = rollNumber

    }
}