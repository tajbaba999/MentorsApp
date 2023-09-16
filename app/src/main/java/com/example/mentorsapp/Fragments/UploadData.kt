package com.example.mentorsapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import com.example.mentorsapp.R
import com.example.mentorsapp.R.layout

class UploadData : Fragment() {

    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var adapterItems : ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout
        val view = inflater.inflate(layout.fragment_upload_data, container, false)


        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView)

        val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")

        adapterItems = ArrayAdapter(requireContext(), R.layout.list_item, items)
        autoCompleteTextView.setAdapter(adapterItems)

        autoCompleteTextView.threshold = 1


        autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position) as String
            handleItemSelected(selectedItem)
        }
        return view
    }

    private fun handleItemSelected(selectedItem: String) {
        Toast.makeText(requireContext(),"Selected : ${selectedItem.toString()}", Toast.LENGTH_LONG).show()
    }
}


