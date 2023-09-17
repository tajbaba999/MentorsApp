package com.example.mentorsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mentorsapp.Fragments.HomeFragment
import com.example.mentorsapp.Fragments.Profile
import com.example.mentorsapp.Fragments.UploadData
//import com.example.mentorsapp.Fragments.UploadData
import com.example.mentorsapp.Fragments.UploadWork
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
import java.util.Calendar

class MainActivity : AppCompatActivity(), OnBackPressedListener {
    lateinit var binding: ActivityMainBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var stdlist : List<String>
    lateinit var recyclerView : RecyclerView
    lateinit var rollAdapter: RollAdapter
    private var doubleBackToExitPressedOnce = false
    private var onBackPressedListener: OnBackPressedListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFragment(HomeFragment())

        firebaseAuth = Firebase.auth

        binding.bottomnavigation.setOnItemSelectedListener{

            when(it.itemId){
                R.id.home-> showFragment(HomeFragment())
                R.id.profile-> showFragment(Profile())
                R.id.uploadWork-> showFragment(UploadWork())
                R.id.uploadstdDeatils-> showFragment(UploadData())
                else-> false
            }
            return@setOnItemSelectedListener true

        }

        fun setOnBackPressedListener(listener: OnBackPressedListener?) {
            onBackPressedListener = listener
        }
    }
    private  fun showFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.framelayout, fragment)
        transaction.addToBackStack(null)
//        transaction.disallowAddToBackStack()
        transaction.commit()
        }


}
