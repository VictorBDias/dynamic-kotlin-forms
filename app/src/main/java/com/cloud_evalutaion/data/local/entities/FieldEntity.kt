package com.cloud_evalutaion.data.local.entities

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
