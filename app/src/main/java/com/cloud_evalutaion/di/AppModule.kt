package com.cloud_evalutaion.di

import android.content.Context
import androidx.room.Room
import com.cloud_evalutaion.data.local.dao.FieldDao
import com.cloud_evalutaion.data.local.dao.FormDao
import com.cloud_evalutaion.data.local.dao.FormEntryDao
import com.cloud_evalutaion.data.local.dao.SectionDao
import com.cloud_evalutaion.data.local.database.AppDatabase
import com.cloud_evalutaion.data.repository.FieldRepository
import com.cloud_evalutaion.data.repository.FormRepository
import com.cloud_evalutaion.data.repository.SectionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFormDao(database: AppDatabase): FormDao {
        return database.formDao()
    }

    @Provides
    @Singleton
    fun provideFieldDao(database: AppDatabase): FieldDao {
        return database.fieldDao()
    }

    @Provides
    @Singleton
    fun provideSectionDao(database: AppDatabase): SectionDao {
        return database.sectionDao()
    }

    @Provides
    @Singleton
    fun provideFormEntryDao(database: AppDatabase): FormEntryDao {
        return database.formEntryDao()
    }

    @Provides
    @Singleton
    fun provideFormRepository(
        formDao: FormDao,
        fieldDao: FieldDao,
        sectionDao: SectionDao
    ): FormRepository {
        return FormRepository(formDao, fieldDao, sectionDao)
    }

    @Provides
    @Singleton
    fun provideFieldRepository(fieldDao: FieldDao): FieldRepository {
        return FieldRepository(fieldDao)
    }

    @Provides
    @Singleton
    fun provideSectionRepository(sectionDao: SectionDao): SectionRepository {
        return SectionRepository(sectionDao)
    }
}
