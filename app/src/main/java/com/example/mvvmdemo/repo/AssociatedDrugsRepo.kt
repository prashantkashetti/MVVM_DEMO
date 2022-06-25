package com.example.mvvmdemo.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mvvmdemo.R
import com.example.mvvmdemo.db.AppDatabase
import com.example.mvvmdemo.db.AssociatedDrugEntity
import com.example.mvvmdemo.models.ApiResponse
import com.google.gson.Gson

interface AssociatedDrugsRepo {
    suspend fun getList(): ApiResponse

    suspend fun saveAssociatedDrugList(associatedDrugs: List<AssociatedDrugEntity>)

    fun getAssociatedDrugList(): LiveData<List<AssociatedDrugEntity>>

    suspend fun deleteAssociatedDrugList()
}

class AssociatedDrugsRepoImpl(private val context: Context, private val appDatabase: AppDatabase) : AssociatedDrugsRepo {

    override suspend fun getList(): ApiResponse {
        val json = context.resources.openRawResource(R.raw.medications).bufferedReader().use { it.readText() }
        return Gson().fromJson(json, ApiResponse::class.java)
    }

    override suspend fun saveAssociatedDrugList(associatedDrugs: List<AssociatedDrugEntity>) {
        appDatabase.associatedDrugDao().insertAll(associatedDrugs)
    }

    override fun getAssociatedDrugList(): LiveData<List<AssociatedDrugEntity>> {
        return appDatabase.associatedDrugDao().getAllDrugs()
    }

    override suspend fun deleteAssociatedDrugList() {
        appDatabase.associatedDrugDao().deleteAll()
    }
}