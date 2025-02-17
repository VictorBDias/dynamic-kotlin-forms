package com.cloud_evalutaion.data.repository

import com.cloud_evalutaion.data.local.dao.SectionDao
import com.cloud_evalutaion.data.local.entities.SectionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SectionRepository @Inject constructor(
    private val sectionDao: SectionDao
) {
    fun getSectionsByFormId(formId: String): Flow<List<SectionEntity>> {
        return sectionDao.getSectionsByFormId(formId)
    }

    suspend fun addSection(section: SectionEntity) {
        sectionDao.addSection(section)
    }

    suspend fun deleteSection(sectionId: String) {
        sectionDao.deleteSection(sectionId)
    }
}
