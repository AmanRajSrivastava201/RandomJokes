package com.example.mytestproject

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mytestproject.data.model.JokesItem
import com.example.mytestproject.viewModel.MainViewModel
import com.example.mytestproject.viewModel.MainViewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    lateinit var viewModel: MainViewModel
    private lateinit var mTextView: TextView
    private lateinit var mNextButton: Button
    private lateinit var mFvrtButton: Button
    private var jokeItem: JokesItem? = null

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as BaseApplication).daggerComponent.inject(this)
        initView()
        initViewModel()
        getNextJoke()
    }


    private fun initView() {
        mTextView = findViewById(R.id.jokes_message)
        mNextButton = findViewById(R.id.button_Next)
        mFvrtButton = findViewById(R.id.button_fvrt)
        mNextButton.setOnClickListener {
            getNextJoke()
        }
        mFvrtButton.setOnClickListener(View.OnClickListener {
            viewModel.markJokeAsFavourite(it.id)
        })

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.jokeItem.observe(this, Observer {
            jokeItem = it
            mTextView.text = it.getFinalJoke()
        })
    }

    private fun getNextJoke() {
        viewModel.getNextJoke()
    }

    // mark favorite
}
