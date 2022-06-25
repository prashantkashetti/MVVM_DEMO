package com.example.mvvmdemo

import android.app.Application
import androidx.room.Room
import com.example.mvvmdemo.db.AppDatabase

class MyApplication : Application() {
    val database by lazy { Room.databaseBuilder(this, AppDatabase::class.java, "mvvm_demo").build() }
}