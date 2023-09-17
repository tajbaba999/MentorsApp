package com.example.mentorsapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MentorViewModel :ViewModel() {
    val mentorData : MutableLiveData<List<String>> =MutableLiveData()
}