package com.example.mvvmdemo.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Problems(
    @SerializedName("Diabetes") var Diabetes: ArrayList<Diabetes> = arrayListOf(),
)

data class Diabetes(
    @SerializedName("medications") var medications: ArrayList<Medications> = arrayListOf(),
)

data class Medications(
    @SerializedName("medicationsClasses") var medicationsClasses: ArrayList<MedicationsClasses> = arrayListOf(),
)

data class MedicationsClasses(
    @SerializedName("className") var className: ArrayList<ClassName> = arrayListOf(),
    @SerializedName("className2") var className2: ArrayList<ClassName> = arrayListOf(),
)

data class ClassName(
    @SerializedName("associatedDrug") var associatedDrug: ArrayList<AssociatedDrug> = arrayListOf(),
    @SerializedName("associatedDrug#2") var associatedDrug2: ArrayList<AssociatedDrug> = arrayListOf(),
)

@Parcelize
data class AssociatedDrug(
    @SerializedName("name") var name: String? = null,
    @SerializedName("dose") var dose: String? = null,
    @SerializedName("strength") var strength: String? = null,
) : Parcelable