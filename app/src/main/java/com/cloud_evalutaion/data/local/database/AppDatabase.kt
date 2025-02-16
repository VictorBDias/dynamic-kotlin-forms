package com.cloud_evalutaion.data.local.database

@Database(entities = [FormEntity::class, FieldEntity::class, FormEntryEntity::class], version = 1)
@TypeConverters(DateConverter::class)  // To handle Date type
abstract class AppDatabase : RoomDatabase() {
    abstract fun formDao(): FormDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "form_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
