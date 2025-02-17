package com.cloud_evalutaion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloud_evalutaion.data.local.entities.SectionEntity
import com.cloud_evalutaion.data.repository.SectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SectionViewModel @Inject constructor(
    private val repository: SectionRepository
) : ViewModel() {

    fun getSectionsByFormId(formId: String): Flow<List<SectionEntity>> {
        return repository.getSectionsByFormId(formId)
    }

    fun addSection(section: SectionEntity) {
        viewModelScope.launch {
            repository.addSection(section)
        }
    }
}
