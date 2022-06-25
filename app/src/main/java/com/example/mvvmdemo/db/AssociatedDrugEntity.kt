package com.example.mvvmdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "associated_drug")
data class AssociatedDrugEntity(
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "dose") var dose: String? = null,
    @ColumnInfo(name = "strength") var strength: String? = null,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}