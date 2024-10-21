package com.example.mytestproject

import android.app.Application
import com.example.mytestproject.di.AppComponent
import com.example.mytestproject.di.DaggerAppComponent

class BaseApplication : Application() {
    lateinit var daggerComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        daggerComponent = DaggerAppComponent.factory().create(this)
    }
}