package com.example.mentorsapp

import com.google.gson.annotations.SerializedName

data class StudentDetails(
    @SerializedName("Caste") val Caste: String,
    @SerializedName("_id") val _id: String,
    @SerializedName("aadharno") val aadharno: String,
    @SerializedName("dob") val dob: String,
    @SerializedName("fathermail") val fathermail: String,
    @SerializedName("fathername") val fathername: String,
    @SerializedName("fatherno") val fatherno: Long,
    @SerializedName("gender") val gender: String,
    @SerializedName("intermarks") val intermarks: Int,
    @SerializedName("medium") val medium: String,
    @SerializedName("mothername") val mothername: String,
    @SerializedName("motherno") val motherno: Long,
    @SerializedName("name") val name: String,
    @SerializedName("permadd") val permadd: String,
    @SerializedName("pressadd") val pressadd: String,
    @SerializedName("rank") val rank: Int,
    @SerializedName("religion") val religion: String,
    @SerializedName("rollno") val rollno: String,
    @SerializedName("sscmarks") val sscmarks: Int,
    @SerializedName("stdAdim") val stdAdim: String,
    @SerializedName("stdmail")  val stdmail: String,
    @SerializedName("stdno") val stdno: Long
)