package com.cloud_evalutaion.ui.forms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloud_evalutaion.data.local.entities.FormEntity
import com.cloud_evalutaion.data.repository.FormRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

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
            repository.insertForm(FormEntity(title = title, timestamp = Date()))
        }
    }

    fun deleteForm(id: String) {
        viewModelScope.launch {
            repository.deleteForm(id)
        }
    }
}
