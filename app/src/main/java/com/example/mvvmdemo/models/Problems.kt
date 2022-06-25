package com.example.mvvmdemo.models

import com.google.gson.annotations.SerializedName


data class Problems(
    @SerializedName("Diabetes") var Diabetes: ArrayList<Diabetes> = arrayListOf(),
    @SerializedName("Asthma") var Asthma: ArrayList<Asthma> = arrayListOf(),
)