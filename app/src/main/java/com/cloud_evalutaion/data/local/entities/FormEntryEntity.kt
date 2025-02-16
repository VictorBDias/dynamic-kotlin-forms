package com.cloud_evalutaion.data.local.entities

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
    val data: String  // JSON representation of the form's answers
)
