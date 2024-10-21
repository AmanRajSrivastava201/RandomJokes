package com.example.mytestproject.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mytestproject.data.model.JokesItem


@Database(entities = [JokesItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): JokesDao
}