package com.example.mentorsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mentorsapp.Fragments.HomeFragment
import com.example.mentorsapp.Fragments.Profile
import com.example.mentorsapp.Fragments.UploadData
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

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var stdlist : List<String>
    lateinit var recyclerView : RecyclerView
    lateinit var rollAdapter: RollAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFragment(HomeFragment())

//        val user = Firebase.auth.currentUser

//        recyclerView = binding.recyclerView

//        if (user != null) {
//            binding.emailfirebase.text = user.email.toString()
//        }
//        val currentTime = Calendar.getInstance()
//        val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
//
//        val morningStart = 4
//        val afternoonStart = 12
//        val eveningStart = 18
//
//
//        val greetingMessage = when(currentHour) {
//            in morningStart until afternoonStart -> "Good Morning"
//            in afternoonStart until eveningStart -> "Good Afternoon"
//            else -> "Good Evening"
//        }


//        binding.greetings.text = greetingMessage

//        val url = ""
//        val  imgeViewproile = binding.imageview
//        Glide.with(this).load(url).circleCrop().fitCenter().into(imgeViewproile)
        //api calls


//        val retrofit = RetrofitInstance.createRetrofitInstance()
//        val apiService = retrofit.create(MentorService::class.java)


//        val email = user?.email.toString()
//        val call: Call<MentorDetails> = apiService.getMentorDetails(email)

//        call.enqueue(object  : Callback<MentorDetails>{
//            override fun onResponse(call: Call<MentorDetails>, response: Response<MentorDetails>) {
//                            if(response.isSuccessful){
//                                val mentor: MentorDetails? = response.body()
//
//                                mentor?.let {
////                                    binding.name.text = it.name
////                                    binding.desg.text = it.desg
//////                                    binding.sec.text = it.sec
////                                    binding.phone.text= it.phono.toString()
////                                    stdlist= it.stdarr
//
////                                    Log.d("datas", "$stdlist")
//
//                                    recyclerView.layoutManager =  LinearLayoutManager(this@MainActivity)
//                                    rollAdapter = RollAdapter(stdlist)
//                                    recyclerView.adapter= rollAdapter
//                                }
//
//                            }
//                    }
//
//            override fun onFailure(call: Call<MentorDetails>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "Error : ${t.localizedMessage}", Toast.LENGTH_LONG).show()
//                }
//
//            }
//        )


//        Log.d("datas","${stdlist}")

//        val dataList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")




//        rollAdapter = RollAdapter(dataList)
//        recyclerView.adapter = rollAdapter
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
    }
    private  fun showFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.framelayout, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
        }
}