package com.example.mvvmdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AssociatedDrugEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun associatedDrugDao(): AssociatedDrugDao
}
