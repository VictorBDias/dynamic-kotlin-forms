package com.cloud_evalutaion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloud_evalutaion.data.local.dao.FormEntryDao
import com.cloud_evalutaion.data.local.entities.FormEntryEntity
import com.cloud_evalutaion.data.repository.FormRepository
import com.cloud_evalutaion.model.FieldOption
import com.cloud_evalutaion.model.Form
import com.cloud_evalutaion.model.FormField
import com.cloud_evalutaion.model.FormSection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


@HiltViewModel
class FormDetailViewModel @Inject constructor(
    private val formEntryDao: FormEntryDao,
    private val formRepository: FormRepository
) : ViewModel() {

    var formData: MutableMap<String, String> = mutableMapOf()

    private val _formEntry = MutableStateFlow<FormEntryEntity?>(null)
    val formEntry: StateFlow<FormEntryEntity?> = _formEntry

    private val _form = MutableStateFlow<Form?>(null)
    val form: StateFlow<Form?> = _form

    fun loadFormEntry(entryId: String) {
        viewModelScope.launch {
            val entry = formEntryDao.getFormEntryById(entryId)
            _formEntry.value = entry
            entry?.let { loadExistingData(it) }
        }
    }

    fun loadForm(formId: String) {
        viewModelScope.launch {
            val formEntity = formRepository.getFormById(formId)
            formEntity?.let {
                val fields = formRepository.getFieldsByFormId(formId).first()
                val sections = formRepository.getSectionsByFormId(formId).first()

                _form.value = Form(
                    title = it.title,
                    fields = fields.map { field ->
                        FormField(
                            type = field.type,
                            label = field.label,
                            name = field.name,
                            required = field.required,
                            uuid = field.id,
                            index = field.index,
                            options = field.options?.map { option ->
                                FieldOption(label = option.label, value = option.value)
                            }
                        )
                    },
                    sections = sections.map { section ->
                        FormSection(
                            title = section.title,
                            from = section.from,
                            to = section.to,
                            index = section.index,
                            uuid = section.id
                        )
                    }
                )
            }
        }
    }

    private fun loadExistingData(formEntry: FormEntryEntity) {
        formEntry.data.let {
            val type = object : TypeToken<Map<String, String>>() {}.type
            formData = Gson().fromJson(it, type) ?: mutableMapOf()
        }
    }

    fun saveEntry(
        formEntry: FormEntryEntity,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val sections = form.value?.sections ?: emptyList()

        val requiredFields = form.value?.fields
            ?.filter { field ->
                sections.any { section -> field.index in section.from..section.to }
            }
            ?.filter { it.required }
            ?.map { it.uuid to it.label }
            ?: emptyList()

        val missingFields = requiredFields
            .filter { (uuid, _) -> formData[uuid].isNullOrEmpty() }
            .map { (_, label) -> label }

        if (missingFields.isNotEmpty()) {
            onError("Please fill in the required fields: ${missingFields.joinToString(", ")}")
            return
        }

        val updatedEntry = formEntry.copy(
            timestamp = Date(),
            data = Gson().toJson(formData)
        )

        viewModelScope.launch {
            formEntryDao.addFormEntry(updatedEntry)
            onSuccess()
        }
    }


}