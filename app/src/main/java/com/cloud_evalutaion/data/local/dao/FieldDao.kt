package com.cloud_evalutaion.data.local.dao

import androidx.room.*
import com.cloud_evalutaion.data.local.entities.FieldEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FieldDao {
    @Query("SELECT * FROM field_entity WHERE formId = :formId")
    fun getFieldsByFormId(formId: String): Flow<List<FieldEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addField(field: FieldEntity)
}
