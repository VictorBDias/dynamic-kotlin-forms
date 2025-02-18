package com.cloud_evalutaion.model

data class FormField(
    val type: String,
    val label: String,
    val name: String,
    val required: Boolean,
    val uuid: String,
    val index: Int,
    val options: List<FieldOption>? = null
) {
    val normalizedType: String
        get() {
            val supportedTypes = listOf("description", "dropdown", "text", "number")
            return if (type in supportedTypes) type else "text"
        }
}