package com.example.myapplication.di

import android.content.ContentResolver
import android.content.Context
import com.example.myapplication.BaseApplication
import com.example.myapplication.feature.data.MediaRepositoryImpl
import com.example.myapplication.feature.domain.MediaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideBaseApplication(@ApplicationContext context: Context): BaseApplication {
        return context as BaseApplication
    }

    @Provides
    fun provideContentResolver(@ApplicationContext context: Context): ContentResolver {
        return context.contentResolver
    }

    @Provides
    @Singleton
    fun provideMediaRepository(contentResolver: ContentResolver): MediaRepository {
        return MediaRepositoryImpl(contentResolver)
    }
}