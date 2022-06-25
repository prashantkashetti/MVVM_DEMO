package com.example.mvvmdemo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmdemo.MyApplication
import com.example.mvvmdemo.databinding.FragmentAssociatedDrugsBinding
import com.example.mvvmdemo.mappers.toAssociatedDrug
import com.example.mvvmdemo.models.AssociatedDrug
import com.example.mvvmdemo.models.LoginModel
import com.example.mvvmdemo.repo.AssociatedDrugsRepoImpl
import com.example.mvvmdemo.ui.home.usecase.AssociatedDrugUseCaseImpl
import com.example.mvvmdemo.utils.extensions.getHour
import java.util.*

class AssociatedDrugsFragment : Fragment() {

    private val myApplication by lazy { requireActivity().application as MyApplication }
    private val listRepo by lazy { AssociatedDrugsRepoImpl(requireContext(), myApplication.database) }
    private val associatedDrugUseCase by lazy { AssociatedDrugUseCaseImpl(listRepo) }
    private val associatedDrugsViewModelFactory by lazy { AssociatedDrugsViewModelFactory(associatedDrugUseCase) }
    private val viewModel: AssociatedDrugsViewModel by viewModels { associatedDrugsViewModelFactory }
    private val associatedDrugsAdapter by lazy { AssociatedDrugsAdapter(::onAssociatedDrugClicked) }

    private lateinit var binding: FragmentAssociatedDrugsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAssociatedDrugsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginModel = requireActivity().intent.getParcelableExtra<LoginModel>("loginModel")
        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = associatedDrugsAdapter
        }
        with(viewModel) {
            binding.tvWelcomeMsg.text = getGreetMsg(Date().getHour()).plus(loginModel?.username)
            associatedDrugList.observe(viewLifecycleOwner) { entities ->
                val models = arrayListOf<AssociatedDrug>()
                entities.forEach { models.add(it.toAssociatedDrug()) }
                associatedDrugsAdapter.submitList(models)
            }
            fetchList()
        }
    }

    private fun onAssociatedDrugClicked(associatedDrug: AssociatedDrug) {
        val dest = AssociatedDrugsFragmentDirections.actionNavHomeToAssociatedDrugDetailsFragment(associatedDrug)
        findNavController().navigate(dest)
    }
}