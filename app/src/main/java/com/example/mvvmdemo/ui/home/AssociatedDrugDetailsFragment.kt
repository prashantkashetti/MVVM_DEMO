package com.example.mvvmdemo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mvvmdemo.databinding.FragmentAsscociatedDrugDetailsBinding

class AssociatedDrugDetailsFragment : Fragment() {
    private lateinit var binding: FragmentAsscociatedDrugDetailsBinding
    private val args: AssociatedDrugDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAsscociatedDrugDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val associatedDrug = args.associatedDrug
        with(binding) {
            with(associatedDrug) {
                tvName.text = name
                tvStrength.text = strength
                tvDose.text = if (dose.isNullOrEmpty()) "-" else dose
            }
        }
    }
}