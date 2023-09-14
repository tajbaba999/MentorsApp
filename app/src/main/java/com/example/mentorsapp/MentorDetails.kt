package com.example.mentorsapp

import com.google.gson.annotations.SerializedName

data class MentorDetails(
    @SerializedName("_id")  val _id: String,
    @SerializedName("desg")  val desg: String,
    @SerializedName("maild")  val mailid: String,
    @SerializedName("name")   val name: String,
    @SerializedName("phono")   val phono: Long,
    @SerializedName("sec")   val sec: String,
    @SerializedName("stdarr")   val stdarr: List<String>,
    @SerializedName("stdcnt")    val stdcnt: Int
) {

}