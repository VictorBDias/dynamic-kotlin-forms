package com.cloud_evalutaion.data.repository

import com.cloud_evalutaion.data.local.dao.FormDao
import com.cloud_evalutaion.data.local.entities.FormEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FormRepository @Inject constructor(private val formDao: FormDao) {
    val allForms: Flow<List<FormEntity>> = formDao.getAllForms()

    suspend fun insertForm(form: FormEntity) {
        formDao.insertForm(form)
    }

    suspend fun deleteForm(formId: String) {
        formDao.deleteForm(formId)
    }
}
