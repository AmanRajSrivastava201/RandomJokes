package com.example.mytestproject.di

import android.content.Context
import androidx.room.Room
import com.example.mytestproject.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "JokesDB").build()
    }
}