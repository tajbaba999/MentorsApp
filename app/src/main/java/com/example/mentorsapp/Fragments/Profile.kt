package com.example.mentorsapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mentorsapp.MentorDetails
import com.example.mentorsapp.MentorService
import com.example.mentorsapp.R
import com.example.mentorsapp.RetrofitInstance
import com.example.mentorsapp.RollAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Profile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val user = Firebase.auth.currentUser

        val retrofit = RetrofitInstance.createRetrofitInstance()
        val apiService = retrofit.create(MentorService::class.java)

        val email = user?.email.toString()


        val profileemail = view.findViewById<TextView>(R.id.emailidprofile)
        val name = view.findViewById<TextView>(R.id.mentornameprofile)
        val phone = view.findViewById<TextView>(R.id.phonenumberprofile)
        val section = view.findViewById<TextView>(R.id.secfiletionpro)

        profileemail.text = email
        val call: Call<MentorDetails> = apiService.getMentorDetails(email).apply {
            enqueue(object  : Callback<MentorDetails> {
                override fun onResponse(call: Call<MentorDetails>, response: Response<MentorDetails>) {
                    if(response.isSuccessful){
                        val mentor: MentorDetails? = response.body()

                        mentor?.let {

                            name.text = it.name
                            phone.text = it.phono.toString()
                            section.text = it.sec


                        }

                    }
                }

                override fun onFailure(call: Call<MentorDetails>, t: Throwable) {
                    Toast.makeText(requireContext(), "Error : ${t.localizedMessage}", Toast.LENGTH_LONG).show()
                }

            }
            ) }


        return view
    }


}