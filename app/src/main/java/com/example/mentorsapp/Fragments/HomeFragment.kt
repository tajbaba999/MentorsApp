@file:Suppress("SpellCheckingInspection")

package com.example.mentorsapp.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.SearchEvent
import android.view.View
import android.view.ViewGroup
//import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mentorsapp.MainActivity
import com.example.mentorsapp.MentorDetails
import com.example.mentorsapp.MentorService
import com.example.mentorsapp.MentorViewModel
import com.example.mentorsapp.R
import com.example.mentorsapp.RetrofitInstance
import com.example.mentorsapp.RollAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar
import androidx.appcompat.widget.SearchView

class HomeFragment : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private var stdlist: List<String> = ArrayList()
    private  lateinit var rollAdapter: RollAdapter
    private val mentorViewModel : MentorViewModel by activityViewModels()
    private var doubleBackToExitPressedOnce : Boolean = false
    private lateinit var originalStdList: List<String>
    private var filteredStdList: List<String> = ArrayList()
     @SuppressLint("MissingInflatedId")
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val user = Firebase.auth.currentUser
        var recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val greetings = view.findViewById<TextView>(R.id.greetings)
        val emailfire = view.findViewById<TextView>(R.id.emailfirebase)
        val  search =view.findViewById<androidx.appcompat.widget.SearchView>(R.id.searchView)
         recyclerView = view.findViewById(R.id.recyclerView)

         stdlist = listOf("Item1", "Item2", "Item3")



         mentorViewModel.mentorData.observe(viewLifecycleOwner, Observer {newdata ->
             newdata?.let {
                 rollAdapter = RollAdapter(it)
                 recyclerView.adapter = rollAdapter
             }
         })

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
        val call: Call<MentorDetails> = apiService.getMentorDetails(email).apply {
            enqueue(object  : Callback<MentorDetails>{
                override fun onResponse(call: Call<MentorDetails>, response: Response<MentorDetails>) {
                        if(response.isSuccessful){
                            val mentor: MentorDetails? = response.body()

                            mentor?.let {
                                stdlist= it.stdarr
//                                Log.d("datas", "$stdlist")
//                                recyclerView.layoutManager =  LinearLayoutManager(requireContext())
//                                rollAdapter = RollAdapter(stdlist)
//                                recyclerView.adapter= rollAdapter

                                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                                rollAdapter = RollAdapter(stdlist)
                                recyclerView.adapter = rollAdapter

                            }

                        }
                }

        override fun onFailure(call: Call<MentorDetails>, t: Throwable) {
            Toast.makeText(requireContext(), "Error : ${t.localizedMessage}", Toast.LENGTH_LONG).show()
            }

        }
    ) }
         originalStdList = stdlist


         search.setOnQueryTextListener(object  : SearchView.OnQueryTextListener {
             override fun onQueryTextSubmit(query: String?): Boolean {
                return false
             }

             override fun onQueryTextChange(newText : String?): Boolean {
                 val filteredList = filterList(newText)
                 updateRecyclerView(filteredList)
                 return true
             }

         })

        return view
    }

    private fun updateRecyclerView(filteredStdList: List<String>) {
        val filteredAdapter = RollAdapter(filteredStdList)
        recyclerView.adapter = filteredAdapter
    }

    private fun filterList(query : String?): List<String> {
        if (query != null){
            return originalStdList.filter { it.contains(query, ignoreCase = true) }

        }
        return originalStdList
    }


}


