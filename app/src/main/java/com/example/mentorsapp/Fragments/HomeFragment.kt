@file:Suppress("SpellCheckingInspection")

package com.example.mentorsapp.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
//import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import androidx.fragment.app.viewModels
import com.facebook.shimmer.ShimmerFrameLayout

class HomeFragment : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private var stdlist: MutableList<String> = mutableListOf()
    private  lateinit var rollAdapter: RollAdapter
    private val mentorViewModel : MentorViewModel by viewModels()
    private var doubleBackToExitPressedOnce : Boolean = false
    private lateinit var originalStdList: List<String>
    private var filteredStdList: List<String> = ArrayList()
    private val handler = Handler()
    private var currrentQuery : String? = null
    private val DEBOUNCE_DELAY: Long = 300
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
     @SuppressLint("MissingInflatedId")
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val user = Firebase.auth.currentUser
        var recyclerView: RecyclerView?
         val greetings = view.findViewById<TextView>(R.id.greetings)
        val emailfire = view.findViewById<TextView>(R.id.emailfirebase)
        val  search =view.findViewById<SearchView>(R.id.searchView)
         shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container)
         val shimmerlayout = view.findViewById<LinearLayout>(R.id.shrimmer_layout)

         recyclerView = view.findViewById(R.id.recyclerView)

//         stdlist = listOf("studnet1","studnet2")
//         rollAdapter = RollAdapter(stdlist)
//         recyclerView.adapter = rollAdapter



         mentorViewModel.getStudnetData().observe(viewLifecycleOwner, Observer {newdata ->
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

                                val newStudentData = it.stdarr
                                stdlist.clear()
                                stdlist.addAll(newStudentData)
                                mentorViewModel.clearStudnetData()
                                mentorViewModel.setStudnetData(newStudentData)
                                rollAdapter.updateData(newStudentData)

                                recyclerView.layoutManager =LinearLayoutManager(requireContext())
                                rollAdapter = RollAdapter(newStudentData)
                                recyclerView.adapter = rollAdapter

//                                stdlist= it.stdarr
//                                val newStudentData = it.stdarr
//                                mentorViewModel.clearStudnetData()
//                                mentorViewModel.setStudnetData(newStudentData)
//                                rollAdapter.updateData(newStudentData)

//                                Log.d("datas", "$stdlist")
//                                recyclerView.layoutManager =  LinearLayoutManager(requireContext())
//                                rollAdapter = RollAdapter(stdlist)
//                                recyclerView.adapter= rollAdapter


//                                recyclerView.layoutManager = LinearLayoutManager(requireContext())
//                                rollAdapter = RollAdapter(newStudentData)
//                                recyclerView.adapter = rollAdapter

                                shimmerFrameLayout.stopShimmer()
                                shimmerlayout.visibility =View.GONE


//                                rollAdapter.updateData(stdlist)

                            }

                        }
                }

        override fun onFailure(call: Call<MentorDetails>, t: Throwable) {
            Toast.makeText(requireContext(), "Error : ${t.localizedMessage}", Toast.LENGTH_LONG).show()
            }

        }
    ) }

         recyclerView.layoutManager = LinearLayoutManager(requireContext())
         originalStdList = stdlist


         search.setOnQueryTextListener(object  : SearchView.OnQueryTextListener {
             override fun onQueryTextSubmit(query: String?): Boolean {
                return false
             }

             override fun onQueryTextChange(newText : String?): Boolean {

                 handler.removeCallbacksAndMessages(null)
                 handler.postDelayed({
                     if (newText != null && newText != currrentQuery){
                         currrentQuery  = newText
                         val filteredStdList = filterList(newText)
                         updateRecyclerView(filteredStdList)
                     }
                 },DEBOUNCE_DELAY)
//                 val filteredList = filterList(newText)
//                 updateRecyclerView(filteredList)
                 return true
             }

         })

        return view
    }

    private fun updateRecyclerView(filteredStdList: List<String>) {
         this.filteredStdList =filteredStdList
         rollAdapter.updateData(filteredStdList)
    }

    private fun filterList(query : String?): List<String> {
        if (query != null){
            return originalStdList.filter { it.contains(query, ignoreCase = true) }

        }
        return originalStdList
    }

}


