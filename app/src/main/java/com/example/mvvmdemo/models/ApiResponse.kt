package com.example.mvvmdemo.models

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("problems") var problems: ArrayList<Problems> = arrayListOf(),
)