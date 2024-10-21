package com.example.mytestproject.data.network

import com.example.mytestproject.data.model.JokesItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("joke/Any")
    suspend fun getNextJoke(): Response<JokesItem>
}