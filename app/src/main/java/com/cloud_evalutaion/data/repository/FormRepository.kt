package com.cloud_evalutaion.data.repository

import com.cloud_evalutaion.data.local.dao.FieldDao
import com.cloud_evalutaion.data.local.dao.FormDao
import com.cloud_evalutaion.data.local.dao.SectionDao
import com.cloud_evalutaion.data.local.entities.FieldEntity
import com.cloud_evalutaion.data.local.entities.FormEntity
import com.cloud_evalutaion.data.local.entities.SectionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FormRepository @Inject constructor(
    private val formDao: FormDao,
    private val fieldDao: FieldDao,
    private val sectionDao: SectionDao,
) {
    val allForms: Flow<List<FormEntity>> = formDao.getAllForms()

    suspend fun addForm(form: FormEntity) {
        formDao.addForm(form)
    }

    suspend fun addField(field: FieldEntity) {
        fieldDao.addField(field)
    }

    suspend fun addSection(section: SectionEntity) {
        sectionDao.addSection(section)
    }

    suspend fun formExists(title: String): Boolean {
        return formDao.getFormByTitle(title) != null
    }

    suspend fun getFormById(formId: String): FormEntity? {
        return formDao.getFormById(formId)
    }

    fun getFieldsByFormId(formId: String): Flow<List<FieldEntity>> {
        return fieldDao.getFieldsByFormId(formId)
    }

    fun getSectionsByFormId(formId: String): Flow<List<SectionEntity>> {
        return sectionDao.getSectionsByFormId(formId)
    }
}
