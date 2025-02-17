package com.cloud_evalutaion.di

import android.content.Context
import androidx.room.Room
import com.cloud_evalutaion.data.local.database.AppDatabase
import com.cloud_evalutaion.data.local.dao.FormDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "form_database"
        ).build()
    }

    @Provides
    fun provideFormDao(database: AppDatabase): FormDao {
        return database.formDao()
    }
}
