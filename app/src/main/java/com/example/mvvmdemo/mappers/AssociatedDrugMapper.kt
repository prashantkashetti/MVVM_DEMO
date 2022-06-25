package com.example.mvvmdemo.mappers

import com.example.mvvmdemo.db.AssociatedDrugEntity
import com.example.mvvmdemo.models.AssociatedDrug

fun AssociatedDrug.toAssociatedDrugEntity(): AssociatedDrugEntity {
    val entity = AssociatedDrugEntity()
    entity.name = this.name
    entity.strength = this.strength
    entity.dose = this.dose
    return entity
}

fun AssociatedDrugEntity.toAssociatedDrug(): AssociatedDrug {
    val model = AssociatedDrug()
    model.name = this.name
    model.strength = this.strength
    model.dose = this.dose
    return model
}