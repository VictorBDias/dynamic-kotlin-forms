package com.cloud_evalutaion.data.local.dao

import androidx.room.*
import com.cloud_evalutaion.data.local.entities.FormEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FormEntryDao {
    @Query("SELECT * FROM form_entry_entity WHERE formId = :formId")
    fun getFormEntries(formId: String): Flow<List<FormEntryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFormEntry(entry: FormEntryEntity)

    @Query("SELECT * FROM form_entry_entity WHERE id = :entryId LIMIT 1")
    suspend fun getFormEntryById(entryId: String): FormEntryEntity?
}
