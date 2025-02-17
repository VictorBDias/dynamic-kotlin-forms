package com.cloud_evalutaion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloud_evalutaion.data.local.entities.FieldEntity
import com.cloud_evalutaion.data.repository.FieldRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FieldViewModel @Inject constructor(
    private val repository: FieldRepository
) : ViewModel() {

    fun getFieldsByFormId(formId: String): Flow<List<FieldEntity>> {
        return repository.getFieldsByFormId(formId)
    }

    fun addField(field: FieldEntity) {
        viewModelScope.launch {
            repository.addField(field)
        }
    }
}
