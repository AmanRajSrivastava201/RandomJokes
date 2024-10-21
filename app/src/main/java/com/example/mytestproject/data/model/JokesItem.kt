package com.example.mytestproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mytestproject.enum.JokeType


@Entity
data class JokesItem(
    val delivery: String?,
    val error: Boolean?,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val safe: Boolean?,
    val setup: String?,
    val type: String?,
    val joke: String?,
    val isFavourite: Boolean?
) {

    fun getFinalJoke(): String {
        var finalJoke = ""
        if (type == JokeType.twopart.name) {
            if (setup != null) {
                finalJoke = finalJoke + setup
            }
            if (delivery != null) {
                finalJoke =  finalJoke + "\n" + delivery
            }
        } else {
            return joke ?: ""
        }
        return finalJoke
    }
}