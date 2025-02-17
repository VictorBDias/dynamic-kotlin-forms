package com.cloud_evalutaion.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "field_entity",
    foreignKeys = [ForeignKey(
        entity = FormEntity::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class FieldEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val label: String,
    val name: String,
    val type: String,
    val required: Boolean,
    val options: String?,
    val index: Int,
    val formId: String
)
