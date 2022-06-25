package com.example.mvvmdemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mvvmdemo.db.AssociatedDrugEntity
import com.example.mvvmdemo.ui.home.usecase.AssociatedDrugUseCase
import kotlinx.coroutines.launch

class AssociatedDrugsViewModel(private val associatedDrugUseCase: AssociatedDrugUseCase) : ViewModel() {

    val associatedDrugList: LiveData<List<AssociatedDrugEntity>>
        get() = associatedDrugUseCase.getAssociatedDrugList()

    fun getGreetMsg(hour: Int) = associatedDrugUseCase.getGreetMsg(hour)

    fun fetchList() = viewModelScope.launch {
        associatedDrugUseCase.getList()
    }
}

class AssociatedDrugsViewModelFactory(private val associatedDrugUseCase: AssociatedDrugUseCase) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AssociatedDrugsViewModel(associatedDrugUseCase) as T
    }
}