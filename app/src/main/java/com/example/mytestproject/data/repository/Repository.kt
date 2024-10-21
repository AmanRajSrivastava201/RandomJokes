package com.example.mytestproject.data.repository

import com.example.mytestproject.data.db.AppDatabase
import com.example.mytestproject.data.db.JokesDao
import com.example.mytestproject.data.model.JokesItem
import com.example.mytestproject.data.network.ApiService
import com.example.mytestproject.utils.AppConst
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) {

    suspend fun getNextJoke(): Response<JokesItem>? {
        var attempts = 0
        while (attempts < AppConst.maxApiRetryAttempts) {
            try {
                // Fetch new data from the API
                val result = apiService.getNextJoke()
                if (result.isSuccessful && result.body() != null) {
                    // Check if the item exists in the local database
                    val existingItem = appDatabase.getDao().getItemById(result.body()!!.id)
                    if (existingItem == null) {
                        appDatabase.getDao().insertJokeInHistory(result.body()!!)
                        return result
                    } else {
                        attempts++
                    }
                } else {
                    return null
                }
            } catch (e: Exception) {
                attempts++
                if (attempts >= AppConst.maxApiRetryAttempts) {
                    return null
                }
            }
        }
        return null
    }

    suspend fun markJokesAsFavourite(id: Int, isFvrt: Boolean) {
        return appDatabase.getDao().markJokeFavourite(id, isFvrt)
    }
}

