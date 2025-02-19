package com.cloud_evalutaion.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cloud_evalutaion.data.local.database.FieldOptionConverter
import com.cloud_evalutaion.model.FieldOption
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

@TypeConverters(FieldOptionConverter::class)
data class FieldEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val label: String,
    val name: String,
    val type: String,
    val required: Boolean,
    val options: List<FieldOption>?,
    val index: Int,
    val formId: String
)