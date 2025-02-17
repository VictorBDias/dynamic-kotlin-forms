package com.cloud_evalutaion.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloud_evalutaion.data.local.dao.FormDao
import com.cloud_evalutaion.data.local.entities.FormEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val formDao: FormDao
) : ViewModel() {

    val allForms: Flow<List<FormEntity>> = formDao.getAllForms()

    fun insertForm(form: FormEntity) {
        viewModelScope.launch {
            formDao.insertForm(form)
        }
    }

    fun deleteForm(formId: String) {
        viewModelScope.launch {
            formDao.deleteForm(formId)
        }
    }
}
