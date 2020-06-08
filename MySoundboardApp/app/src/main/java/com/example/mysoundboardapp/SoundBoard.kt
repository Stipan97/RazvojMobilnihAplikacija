package com.example.mysoundboardapp

import android.app.Application
import android.content.Context

class SoundBoard: Application() {
    companion object {
        lateinit var ApplicationContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        ApplicationContext = this
    }
}