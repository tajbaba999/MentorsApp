package com.example.mentorsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MentorViewModel :ViewModel() {

    val studentData: MutableLiveData<List<String>> = MutableLiveData()

    fun setStudnetData(dat : List<String>){
        studentData.value = dat
    }

    fun getStudnetData() : LiveData<List<String>>{
        return studentData
    }

    fun clearStudnetData() {
        studentData.value = emptyList()
    }
}