package com.example.mentorsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mentorsapp.databinding.ActivityRegestrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Regestration : AppCompatActivity() {
    lateinit var binding: ActivityRegestrationBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegestrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth
        firebaseAuth = FirebaseAuth.getInstance()
        binding.regesterbtn.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password1 = binding.TextPassword1.text.toString()
            val password2 = binding.TextPassword2.text.toString()

            if (password1.isNotEmpty() && password2.isNotEmpty() && email.isNotEmpty()){
                if (password1 == password2){
                        firebaseAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener {
                            if (it.isSuccessful){
                                Toast.makeText(this, it.result.toString(), Toast.LENGTH_SHORT)
                                val intent = Intent(this, loginpage::class.java)
                                startActivity(intent)
                            }
                            else{
                                Toast.makeText(this, it.exception.toString(),Toast.LENGTH_SHORT).show()
                            }
                        }
                }
                else{
                    Toast.makeText(this, "Passwords are not matching",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Please Fill are fields",Toast.LENGTH_SHORT).show()
            }
        }

    }
}