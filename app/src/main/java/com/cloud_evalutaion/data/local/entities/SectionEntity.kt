package com.cloud_evalutaion.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "section_entity",
    indices = [Index(value = ["formId"])],
    foreignKeys = [ForeignKey(
        entity = FormEntity::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class SectionEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String,
    val from: Int,
    val to: Int,
    val index: Int,
    val uuid: String,
    val formId: String
)
