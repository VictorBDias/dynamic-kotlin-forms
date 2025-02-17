package com.cloud_evalutaion.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.cloud_evalutaion.data.local.dao.FieldDao
import com.cloud_evalutaion.data.local.dao.FormDao
import com.cloud_evalutaion.data.local.dao.FormEntryDao
import com.cloud_evalutaion.data.local.dao.SectionDao
import com.cloud_evalutaion.data.local.entities.FieldEntity
import com.cloud_evalutaion.data.local.entities.FormEntity
import com.cloud_evalutaion.data.local.entities.FormEntryEntity
import com.cloud_evalutaion.data.local.entities.SectionEntity
import java.util.Date


class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}


@Database(entities = [FormEntity::class, FieldEntity::class, FormEntryEntity::class, SectionEntity::class], version = 2)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun formDao(): FormDao
    abstract fun fieldDao(): FieldDao
    abstract fun sectionDao(): SectionDao
    abstract fun formEntryDao(): FormEntryDao

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
