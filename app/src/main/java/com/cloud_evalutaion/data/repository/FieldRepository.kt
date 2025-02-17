package com.cloud_evalutaion.data.repository

import com.cloud_evalutaion.data.local.dao.FieldDao
import com.cloud_evalutaion.data.local.entities.FieldEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FieldRepository @Inject constructor(
    private val fieldDao: FieldDao
) {
    fun getFieldsByFormId(formId: String): Flow<List<FieldEntity>> {
        return fieldDao.getFieldsByFormId(formId)
    }

    suspend fun addField(field: FieldEntity) {
        fieldDao.addField(field)
    }

    suspend fun deleteField(fieldId: String) {
        fieldDao.deleteField(fieldId)
    }
}
