package com.cloud_evalutaion.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "form_entity")
data class FormEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),  // Unique ID
    val title: String,
    val timestamp: Date
)
