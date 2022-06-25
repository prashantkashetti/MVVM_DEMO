package com.example.mvvmdemo.ui.home.usecase

import androidx.lifecycle.LiveData
import com.example.mvvmdemo.db.AssociatedDrugEntity
import com.example.mvvmdemo.mappers.toAssociatedDrugEntity
import com.example.mvvmdemo.repo.AssociatedDrugsRepo

interface AssociatedDrugUseCase {
    fun getGreetMsg(hour: Int): String

    suspend fun getList()

    fun getAssociatedDrugList(): LiveData<List<AssociatedDrugEntity>>
}

class AssociatedDrugUseCaseImpl(private val associatedDrugsRepo: AssociatedDrugsRepo) : AssociatedDrugUseCase {

    override fun getGreetMsg(hour: Int): String {
        return when (hour) {
            in 12..16 -> {
                "Good Afternoon"
            }
            in 17..20 -> {
                "Good Evening"
            }
            in 21..23 -> {
                "Good Night"
            }
            else -> {
                "Good Morning"
            }
        }
    }

    override suspend fun getList() {
        val apiResponse = associatedDrugsRepo.getList()
        val associatedDrugs = arrayListOf<AssociatedDrugEntity>()
        apiResponse.problems.forEach { problem ->
            problem.Diabetes.forEach { diabetes ->
                diabetes.medications.forEach { medications ->
                    medications.medicationsClasses.forEach { classes ->
                        classes.className.forEach { className ->
                            className.associatedDrug.forEach { associatedDrugs.add(it.toAssociatedDrugEntity()) }
                            className.associatedDrug2.forEach { associatedDrugs.add(it.toAssociatedDrugEntity()) }
                        }
                        classes.className2.forEach { className ->
                            className.associatedDrug.forEach { associatedDrugs.add(it.toAssociatedDrugEntity()) }
                            className.associatedDrug2.forEach { associatedDrugs.add(it.toAssociatedDrugEntity()) }
                        }
                    }
                }
            }
        }
        deleteAssociatedDrugList()
        saveAssociatedDrugList(associatedDrugs)
    }

    override fun getAssociatedDrugList(): LiveData<List<AssociatedDrugEntity>> {
        return associatedDrugsRepo.getAssociatedDrugList()
    }

    private suspend fun saveAssociatedDrugList(associatedDrugs: List<AssociatedDrugEntity>) {
        associatedDrugsRepo.saveAssociatedDrugList(associatedDrugs)
    }

    private suspend fun deleteAssociatedDrugList() {
        associatedDrugsRepo.deleteAssociatedDrugList()
    }
}