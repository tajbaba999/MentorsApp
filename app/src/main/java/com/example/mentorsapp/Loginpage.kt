package com.example.mentorsapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mentorsapp.databinding.ActivityLoginpageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Loginpage : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var binding: ActivityLoginpageBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginpageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = Firebase.auth
        firebaseAuth = FirebaseAuth.getInstance()

        binding.bottmtext.setOnClickListener {
            val intent = Intent(this, Regestration::class.java)
            startActivity(intent)
        }

        binding.btnlog.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val pass = binding.editTextPassword.text.toString()

             if (email.isNotEmpty() && pass.isNotEmpty()){
                 firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                     if (it.isSuccessful){
                         val intent = Intent(this, MainActivity::class.java)
                         startActivity(intent)
                     }else{
                         Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                     }
                 }
             }
            else{
                Toast.makeText(this, "Please Fill the mandatoery Fields",Toast.LENGTH_SHORT).show()
             }
        }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser !=  null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}