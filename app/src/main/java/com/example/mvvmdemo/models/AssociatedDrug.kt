package com.example.mvvmdemo.models

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AssociatedDrug(
    @SerializedName("name") var name: String? = null,
    @SerializedName("dose") var dose: String? = null,
    @SerializedName("strength") var strength: String? = null,
) : Parcelable

class AssociatedDrugDiffUtils: DiffUtil.ItemCallback<AssociatedDrug>(){
    override fun areItemsTheSame(oldItem: AssociatedDrug, newItem: AssociatedDrug): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: AssociatedDrug, newItem: AssociatedDrug): Boolean {
        return oldItem == newItem
    }
}