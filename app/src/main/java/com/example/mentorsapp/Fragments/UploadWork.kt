package com.example.mentorsapp.Fragments

//import android.Manifest
//import android.annotation.SuppressLint
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.ProgressBar
//import android.widget.Toast
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import com.example.mentorsapp.R
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.storage.FirebaseStorage
//import com.google.firebase.storage.StorageReference


//class UploadWork : Fragment() {
//
//    private lateinit var stoRef : StorageReference
//    private lateinit var firebaseFirestore: FirebaseFirestore
//    private lateinit var processBar: ProgressBar
//    private var imgeUri : Uri?= null
//    private lateinit var imageViewData: ImageView
//
//    //    @SuppressLint("MissingInflatedId")
////    @SuppressLint("MissingInflatedId")
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view= inflater.inflate(R.layout.fragment_upload_work, container, false)
//        processBar =view.findViewById(R.id.progressBar)
//        imageViewData = view.findViewById(R.id.imageViewData)
//
//
//
//        initVars()
//        registerClickEvents()
//
//        return view
//    }
//
//    private fun registerClickEvents() {
//        val uplodbtn = view?.findViewById<Button>(R.id.uploadBtn)
//        if (uplodbtn != null) {
//            uplodbtn.setOnClickListener {
//                uploadImage()
//                processBar.visibility = View.VISIBLE
//            }
//        }
//        imageViewData?.setOnClickListener {
//            resultLauncher.launch("Images/*")
//        }
//
//    }
//
//
//
//    private val resultLauncher = registerForActivityResult(
//        ActivityResultContracts.GetContent()
//    ) {
//        imgeUri = it
//        imageViewData?.setImageURI(it)
//
//    }
//
//
//
//    private fun initVars() {
//        stoRef = FirebaseStorage.getInstance().reference.child("Images")
//        firebaseFirestore = FirebaseFirestore.getInstance()
//    }
//
//    private fun uploadImage() {
//        stoRef = stoRef.child(System.currentTimeMillis().toString())
//        imgeUri?.let {
//            stoRef.putFile(it).addOnCompleteListener{ task->
//                if (task.isSuccessful){
//                    stoRef.downloadUrl.addOnSuccessListener {uri->
//                        val map = HashMap<String, Any>()
//                        map["pic"] = uri.toString()
//                        firebaseFirestore.collection("images").add(map).addOnCompleteListener { firestoretask ->
//                            if (firestoretask.isSuccessful){
//                                Toast.makeText(requireContext(), "Image is uploaded Sucessfully",
//                                    Toast.LENGTH_LONG)
//                            }
//                            else{
//                                Toast.makeText(requireContext(), "${firestoretask.exception?.message}",
//                                    Toast.LENGTH_LONG)
//                            }
//                            processBar.visibility = View.GONE
//                            imageViewData.setImageResource(R.drawable.imagevector)
//
//                        }
//                    }
//
//                }
//                else{
//                    Toast.makeText(requireContext(), "${task.exception?.message}", Toast.LENGTH_LONG)
//                }
//            }
//        }
//    }
//}


import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.mentorsapp.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class UploadWork : Fragment() {

    private lateinit var stoRef: StorageReference
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var processBar: ProgressBar
    private var imgeUri: Uri? = null
    private lateinit var imageViewData: ImageView

    companion object {
        private const val REQUEST_CODE_STORAGE_PERMISSION = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upload_work, container, false)
        processBar = view.findViewById(R.id.progressBar)
        imageViewData = view.findViewById(R.id.imageViewData)

        initVars()
        registerClickEvents()

        return view
    }

    private fun registerClickEvents() {
        val uplodbtn = view?.findViewById<Button>(R.id.uploadBtn)
        if (uplodbtn != null) {
            uplodbtn.setOnClickListener {
                uploadImage()
                processBar.visibility = View.VISIBLE
            }
        }
        imageViewData?.setOnClickListener {
            if (checkPermission()) {
                openGallery()
            } else {
                requestPermission()
            }
        }
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_CODE_STORAGE_PERMISSION
        )
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        imgeUri = it
        imageViewData?.setImageURI(it)
    }

    private fun initVars() {
        stoRef = FirebaseStorage.getInstance().reference.child("Images")
        firebaseFirestore = FirebaseFirestore.getInstance()
    }

    private fun uploadImage() {
        stoRef = stoRef.child(System.currentTimeMillis().toString())
        imgeUri?.let {
            stoRef.putFile(it).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    stoRef.downloadUrl.addOnSuccessListener { uri ->
                        val map = HashMap<String, Any>()
                        map["pic"] = uri.toString()
                        firebaseFirestore.collection("images").add(map)
                            .addOnCompleteListener { firestoretask ->
                                if (firestoretask.isSuccessful) {
                                    Toast.makeText(
                                        requireContext(),
                                        "Image is uploaded Sucessfully",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "${firestoretask.exception?.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                                processBar.visibility = View.GONE
                                imageViewData.setImageResource(R.drawable.imagevector)
                            }
                    }
                } else {
                    Toast.makeText(requireContext(), "${task.exception?.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun openGallery() {
        resultLauncher.launch("image/*")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, open the gallery
                openGallery()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Storage permission denied. Cannot open gallery.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
