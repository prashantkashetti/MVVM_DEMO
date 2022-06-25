package com.example.mvvmdemo.models

import com.google.gson.annotations.SerializedName

data class Medications(
    @SerializedName("medicationsClasses") var medicationsClasses: ArrayList<MedicationsClasses> = arrayListOf(),
)