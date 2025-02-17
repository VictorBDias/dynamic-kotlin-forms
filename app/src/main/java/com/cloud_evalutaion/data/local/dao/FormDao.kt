package com.cloud_evalutaion.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cloud_evalutaion.data.local.entities.FormEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FormDao {
    @Query("SELECT * FROM form_entity")
    fun getAllForms(): Flow<List<FormEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForm(form: FormEntity)

    @Query("DELETE FROM form_entity WHERE id = :formId")
    suspend fun deleteForm(formId: String)
}
