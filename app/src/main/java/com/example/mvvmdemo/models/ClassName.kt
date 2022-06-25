package com.example.mvvmdemo.models

import com.google.gson.annotations.SerializedName


data class ClassName(
    @SerializedName("associatedDrug") var associatedDrug: ArrayList<AssociatedDrug> = arrayListOf(),
    @SerializedName("associatedDrug#2") var associatedDrug2: ArrayList<AssociatedDrug> = arrayListOf(),
)