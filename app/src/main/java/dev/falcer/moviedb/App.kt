package dev.falcer.moviedb

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@Suppress("unused")
@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance : App
            private set
    }
}