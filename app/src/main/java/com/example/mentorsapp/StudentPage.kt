package com.example.mentorsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mentorsapp.databinding.ActivityStudentpageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class StudentPage : AppCompatActivity() {
    lateinit var binding: ActivityStudentpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentpageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val rollNumber = intent.getStringExtra("rollNumber")
        binding.rollNumber.text = rollNumber

        val retrofit = RetrofitInstance.createRetrofitInstance()
        val apiService = retrofit.create(StudentService::class.java)

        val call : Call<StudentDetails>? = rollNumber?.let { apiService.getStudentDetails(it) }

        if (call != null) {
            call.enqueue(object : Callback<StudentDetails>{
                override fun onResponse( call: Call<StudentDetails>, response: Response<StudentDetails>) {
                    if (response.isSuccessful){
                        val student : StudentDetails? = response.body()

                        student?.let {
                                        binding.nametV.text = student.name
                                        binding.stdNumbertV.text= student.stdno.toString()
                                        binding.addresstV.text = student.permadd
                        }
                    }

                }

                override fun onFailure(call: Call<StudentDetails>, t: Throwable) {
                    Toast.makeText(this@StudentPage,"Error : ${t.localizedMessage}", Toast.LENGTH_LONG).show()
                }

            })
        }

        val intent = Intent(this@StudentPage,UploadImages::class.java)
        startActivity(intent)

    }
}


