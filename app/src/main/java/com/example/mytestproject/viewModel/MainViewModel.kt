package com.example.mytestproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestproject.data.model.JokesItem
import com.example.mytestproject.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _jokeItem = MutableLiveData<JokesItem>()
    val jokeItem: LiveData<JokesItem> get() = _jokeItem


    fun getNextJoke() {
        // dispatcher
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            val result = repository.getNextJoke()
            if (result != null && result.isSuccessful && result.body() != null) {
                withContext(Dispatchers.Main) {
                    _jokeItem.postValue(result.body())
                }
            }
        }

    }

    fun markJokeAsFavourite(id: Int) {
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            repository.markJokesAsFavourite(id, true)
        }
    }
}