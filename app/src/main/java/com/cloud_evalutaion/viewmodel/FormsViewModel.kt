package com.cloud_evalutaion.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloud_evalutaion.data.local.entities.FieldEntity
import com.cloud_evalutaion.data.local.entities.FormEntity
import com.cloud_evalutaion.data.local.entities.SectionEntity
import com.cloud_evalutaion.data.repository.FormRepository
import com.cloud_evalutaion.model.Form
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import java.util.Date
import java.util.UUID
import javax.inject.Inject
import com.google.gson.Gson


@HiltViewModel
class FormsViewModel @Inject constructor(
    private val repository: FormRepository
) : ViewModel() {

    val forms: StateFlow<List<FormEntity>> = repository.allForms.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    fun addForm(title: String) {
        viewModelScope.launch {
            repository.addForm(FormEntity(title = title, timestamp = Date()))
        }
    }

    fun loadFormsFromJson(context: Context) {
        viewModelScope.launch {
            val formFiles = listOf("200-form.json", "all-fields.json")

            for (fileName in formFiles) {
                try {
                    val inputStream = context.assets.open(fileName)
                    val reader = InputStreamReader(inputStream)
                    val formJson = Gson().fromJson<Form>(
                        reader, object : TypeToken<Form>() {}.type
                    )

                    val formExists = repository.formExists(formJson.title)
                    if (!formExists) {
                        val formEntity = FormEntity(
                            id = UUID.randomUUID().toString(),
                            title = formJson.title,
                            timestamp = Date()
                        )
                        repository.addForm(formEntity)

                        for ((index, field) in formJson.fields.withIndex()) {
                            val fieldEntity = FieldEntity(
                                id = field.uuid,
                                label = field.label,
                                name = field.name,
                                type = field.type,
                                required = field.required ?: false,
                                options = field.options?.joinToString(",") { it.value },
                                index = index,
                                formId = formEntity.id
                            )
                            repository.addField(fieldEntity)
                        }

                        if (formJson.sections != null) {
                            for (section in formJson.sections) {
                                val sectionEntity = SectionEntity(
                                    id = UUID.randomUUID().toString(),
                                    title = section.title,
                                    from = section.from,
                                    to = section.to,
                                    index = section.index,
                                    uuid = section.uuid,
                                    formId = formEntity.id
                                )
                                repository.addSection(sectionEntity)
                            }
                        }
                    }
                } catch (e: Exception) {
                    println("📢 Error loading JSON")
                    e.printStackTrace()
                }
            }
        }
    }
}
