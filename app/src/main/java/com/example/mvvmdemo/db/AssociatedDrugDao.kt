package com.example.mvvmdemo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AssociatedDrugDao {
    @Insert
    suspend fun insertAll(associatedDrugs: List<AssociatedDrugEntity>)

    @Query("SELECT * from associated_drug")
    fun getAllDrugs(): LiveData<List<AssociatedDrugEntity>>

    @Query("DELETE from associated_drug")
    suspend fun deleteAll()
}