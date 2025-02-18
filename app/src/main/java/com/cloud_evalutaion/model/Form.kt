package com.cloud_evalutaion.model


data class Form(
    val title: String,
    val fields: List<FormField>,
    val sections: List<FormSection>? = null,
)