package com.cloud_evalutaion.model

import com.cloud_evalutaion.data.model.FormField
import com.cloud_evalutaion.data.model.FormSection

data class Form(
    val title: String,
    val fields: List<FormField>,
    val sections: List<FormSection>? = null
)