package com.example.mytestproject.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mytestproject.data.model.JokesItem
import retrofit2.Response

@Dao
interface JokesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJokeInHistory(jokesItem: JokesItem)

    @Query("SELECT * from JokesItem")
    suspend fun getAllJokes(): Response<List<JokesItem>>

    @Query("SELECT * FROM JokesItem WHERE id = :id")
    suspend fun getItemById(id: Int): JokesItem?

    @Query("UPDATE JokesItem SET isFavourite = :isFavourite WHERE id = :id")
    suspend fun markJokeFavourite(id: Int, isFavourite: Boolean)
}