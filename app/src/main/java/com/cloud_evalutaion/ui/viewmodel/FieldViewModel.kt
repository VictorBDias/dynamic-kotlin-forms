package com.cloud_evalutaion.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloud_evalutaion.data.local.dao.FieldDao
import com.cloud_evalutaion.data.local.entities.FieldEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FieldViewModel @Inject constructor(
    private val fieldDao: FieldDao
) : ViewModel() {

    fun getFieldsByFormId(formId: String): Flow<List<FieldEntity>> {
        return fieldDao.getFieldsByFormId(formId)
    }

    fun insertField(field: FieldEntity) {
        viewModelScope.launch {
            fieldDao.insertField(field)
        }
    }

    fun deleteField(fieldId: String) {
        viewModelScope.launch {
            fieldDao.deleteField(fieldId)
        }
    }
}
