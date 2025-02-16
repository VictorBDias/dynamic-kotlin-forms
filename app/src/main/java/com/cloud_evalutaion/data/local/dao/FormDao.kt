package com.cloud_evalutaion.data.local.dao

@Dao
interface FormDao {
    @Query("SELECT * FROM form_entity")
    fun getAllForms(): Flow<List<FormEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForm(form: FormEntity)

    @Query("DELETE FROM form_entity WHERE id = :formId")
    suspend fun deleteForm(formId: String)
}
