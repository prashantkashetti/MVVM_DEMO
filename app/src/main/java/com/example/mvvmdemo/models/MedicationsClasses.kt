package com.example.mvvmdemo.models

import com.google.gson.annotations.SerializedName

data class MedicationsClasses(
    @SerializedName("className") var className: ArrayList<ClassName> = arrayListOf(),
    @SerializedName("className2") var className2: ArrayList<ClassName> = arrayListOf(),
)