package com.cloud_evalutaion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloud_evalutaion.data.local.dao.FormEntryDao
import com.cloud_evalutaion.data.local.entities.FormEntryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@HiltViewModel
class FormDetailViewModel @Inject constructor(
    private val formEntryDao: FormEntryDao
) : ViewModel() {

    var formData: MutableMap<String, String> = mutableMapOf()

    fun loadExistingData(formEntry: FormEntryEntity) {
        formEntry.data?.let {
            val type = object : TypeToken<Map<String, String>>() {}.type
            formData = Gson().fromJson(it, type) ?: mutableMapOf()
        }
    }

    fun saveEntry(formEntry: FormEntryEntity, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val missingFields = formData.filterValues { it.isEmpty() }.keys
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
