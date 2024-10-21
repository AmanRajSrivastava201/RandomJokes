package com.example.mytestproject.di

import android.content.Context
import com.example.mytestproject.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}