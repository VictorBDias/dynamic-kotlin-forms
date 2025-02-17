package com.cloud_evalutaion.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(
    tableName = "form_entry_entity",
    foreignKeys = [ForeignKey(
        entity = FormEntity::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class FormEntryEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val formId: String,
    val timestamp: Date,
    val data: String
)
