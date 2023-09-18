package com.example.mentorsapp.Fragments
//
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.mentorsapp.MentorViewModel
import com.example.mentorsapp.R
import com.example.mentorsapp.R.layout
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class UploadData : Fragment() {

    private lateinit var autoCompleteTVrollnnumber : AutoCompleteTextView
    private lateinit var autoCompleteTVFee: AutoCompleteTextView
    private lateinit var autoCompleteTVYear: AutoCompleteTextView
    private lateinit var autoCompleteTVSem: AutoCompleteTextView
    private lateinit var adapterItemsRollNumber : ArrayAdapter<String>
    private lateinit var adapterItemsFees : ArrayAdapter<String>
    private lateinit var adapterItemyear : ArrayAdapter<String>
    private lateinit var adapterItemsem : ArrayAdapter<String>
    private var imgeUri : Uri?= null
    private lateinit var imageViewData: ImageView



    private val mentorViewModel : MentorViewModel by activityViewModels ()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout
        val view = inflater.inflate(layout.fragment_upload_data, container, false)
        autoCompleteTVrollnnumber = view.findViewById(R.id.autoCompleteTVrollNumber)
        autoCompleteTVFee =view.findViewById(R.id.autoCompleteTVFeeType)
        autoCompleteTVYear = view.findViewById(R.id.autoCompleteTVyear)
        autoCompleteTVSem =view.findViewById(R.id.autoCompleteSem)



        val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")
        adapterItemsRollNumber = ArrayAdapter(requireContext(), R.layout.list_itemrollnumber, items)

        autoCompleteTVrollnnumber.setAdapter(adapterItemsRollNumber)

        autoCompleteTVrollnnumber.threshold = 1

        val Feetype = listOf("Transportation fee","College Fee","Hostel Fee")
        adapterItemsFees  = ArrayAdapter(requireContext(), R.layout.list_itemfee, Feetype)

        autoCompleteTVFee.setAdapter(adapterItemsFees)

        autoCompleteTVFee.threshold = 1

        val selectYear  = listOf("1st Year","2nd Year","3rd Year", "4th Year" )
        adapterItemyear  = ArrayAdapter(requireContext(), R.layout.list_itemfee, selectYear)

        autoCompleteTVYear.setAdapter(adapterItemyear)

        autoCompleteTVYear.threshold = 1

        val sem  = listOf("SEM I","SEM II",)
        adapterItemsem  = ArrayAdapter(requireContext(), R.layout.list_itemfee, sem)

        autoCompleteTVSem.setAdapter(adapterItemsem)

        autoCompleteTVSem.threshold = 1



//        autoCompleteTVrollnnumber.setOnItemClickListener { parent, _, position, _ ->
//            val selectedItem = parent.getItemAtPosition(position) as String
//            handleItemSelected(selectedItem)
//        }


        mentorViewModel.getStudnetData().observe(viewLifecycleOwner, Observer { data ->
            Log.d("LiveData", "Data: $data")
        })




//        mentorViewModel.getStudnetData().observe(viewLifecycleOwner, Observer { data ->
//            Log.e("dataroll","$data")
////            if (data != null) {
////                adapterItems.clear()
////                adapterItems.addAll(data)
////                autoCompleteTextView.setAdapter(adapterItems)
////            }
//        })

//        mentorViewModel.getStudnetData().observe(viewLifecycleOwner, Observer { data ->
//                Log.d("LiveData", "$data")
//            })




//        mentorViewModel.getStudnetData().observe(viewLifecycleOwner, Observer { studentData ->
//            adapterItems.clear()
//            adapterItems.addAll(studentData)
//            adapterItems.notifyDataSetChanged()
//        })

//
//        autoCompleteTextView.setAdapter(adapterItems)





        return view
    }



    private fun handleItemSelected(selectedItem: String) {
        Toast.makeText(requireContext(),"Selected : ${selectedItem.toString()}", Toast.LENGTH_LONG).show()
    }
}








