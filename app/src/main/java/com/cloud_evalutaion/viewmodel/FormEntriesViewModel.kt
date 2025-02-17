package com.cloud_evalutaion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloud_evalutaion.data.local.dao.FormEntryDao
import com.cloud_evalutaion.data.local.entities.FormEntryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class FormEntriesViewModel @Inject constructor(
    private val formEntryDao: FormEntryDao
) : ViewModel() {

    fun getFormEntries(formId: String): Flow<List<FormEntryEntity>> {
        return formEntryDao.getFormEntries(formId)
    }

    fun addFormEntry(formId: String) {
        viewModelScope.launch {
            val newEntry = FormEntryEntity(
                id = UUID.randomUUID().toString(),
                formId = formId,
                timestamp = Date(),
                data = "{}"
            )
            formEntryDao.addFormEntry(newEntry)
        }
    }


}
