@file:Suppress("SpellCheckingInspection")

package com.example.mentorsapp.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mentorsapp.MentorDetails
import com.example.mentorsapp.MentorService
import com.example.mentorsapp.R
import com.example.mentorsapp.RetrofitInstance
import com.example.mentorsapp.RollAdapter
import com.example.mentorsapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar


class HomeFragment : Fragment() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var recyclerView: RecyclerView
    lateinit var stdlist: List<String>

    lateinit var rollAdapter: RollAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val user = Firebase.auth.currentUser
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val greetings = view.findViewById<TextView>(R.id.greetings)
        val emailfire = view.findViewById<TextView>(R.id.emailfirebase)
        if (user != null) {
            emailfire.text = user.email.toString()
        }

        val currentTime = Calendar.getInstance()
        val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)

        val morningStart = 4
        val afternoonStart = 12
        val eveningStart = 18

        val greetingMessage = when (currentHour) {
            in morningStart until afternoonStart -> "Good Morning"
            in afternoonStart until eveningStart -> "Good Afternoon"
            else -> "Good Evening"
        }
        greetings.text = greetingMessage

        val retrofit = RetrofitInstance.createRetrofitInstance()
        val apiService = retrofit.create(MentorService::class.java)

        val email = user?.email.toString()
        val call: Call<MentorDetails> = apiService.getMentorDetails(email)


        call.enqueue(object : Callback<MentorDetails> {
            override fun onResponse(call: Call<MentorDetails>, response: Response<MentorDetails>) {
                if (response.isSuccessful) {
                    val mentor: MentorDetails? = response.body()
                     val stdlist: List<String>? = ArrayList<String>()

                    mentor?.let {item->
                        recyclerView.layoutManager = LinearLayoutManager(requireContext())
                        rollAdapter = stdlist?.let { RollAdapter(it) }!!
                        recyclerView.adapter = rollAdapter
                    }
                }
            }

            override fun onFailure(call: Call<MentorDetails>, t: Throwable) {
                Toast.makeText(requireContext(), "Error : ${t.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        })

        return view
    }
}
