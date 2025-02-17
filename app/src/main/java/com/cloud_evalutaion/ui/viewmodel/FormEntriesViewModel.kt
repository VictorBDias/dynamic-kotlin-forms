package com.cloud_evalutaion.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloud_evalutaion.data.local.dao.FormEntryDao
import com.cloud_evalutaion.data.local.entities.FormEntryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormEntriesViewModel @Inject constructor(
    private val formEntryDao: FormEntryDao
) : ViewModel() {

    fun getFormEntries(formId: String): Flow<List<FormEntryEntity>> {
        return formEntryDao.getFormEntries(formId)
    }

    fun insertFormEntry(entry: FormEntryEntity) {
        viewModelScope.launch {
            formEntryDao.insertFormEntry(entry)
        }
    }

    fun deleteFormEntry(entryId: String) {
        viewModelScope.launch {
            formEntryDao.deleteFormEntry(entryId)
        }
    }
}
