package com.example.mvvmdemo.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.databinding.AssociatedDrugsListRowBinding
import com.example.mvvmdemo.models.AssociatedDrug
import com.example.mvvmdemo.models.AssociatedDrugDiffUtils

class AssociatedDrugsAdapter(private val onAssociatedDrugClicked: (AssociatedDrug) -> Unit) :
    ListAdapter<AssociatedDrug, AssociatedDrugsViewHolder>(AssociatedDrugDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssociatedDrugsViewHolder {
        val binding = AssociatedDrugsListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AssociatedDrugsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AssociatedDrugsViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model, onAssociatedDrugClicked)
    }
}

class AssociatedDrugsViewHolder(private val binding: AssociatedDrugsListRowBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(associatedDrug: AssociatedDrug, onAssociatedDrugClicked: (AssociatedDrug) -> Unit) {
        with(binding) {
            with(associatedDrug) {
                tvName.text = name
                tvStrength.text = strength
                tvDose.text = if (dose.isNullOrEmpty()) "-" else dose
                root.setOnClickListener {
                    onAssociatedDrugClicked(this)
                }
            }
        }
    }
}