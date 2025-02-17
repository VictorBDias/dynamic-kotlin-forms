package com.cloud_evalutaion.data.local.dao

import androidx.room.*
import com.cloud_evalutaion.data.local.entities.SectionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SectionDao {

    @Query("SELECT * FROM section_entity WHERE formId = :formId ORDER BY `index` ASC")
    fun getSectionsByFormId(formId: String): Flow<List<SectionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSection(section: SectionEntity)

    @Query("DELETE FROM section_entity WHERE id = :sectionId")
    suspend fun deleteSection(sectionId: String)
}
