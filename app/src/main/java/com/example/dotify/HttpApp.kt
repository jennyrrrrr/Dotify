package com.example.dotify
import android.app.Application

class HttpApp: Application() {
    lateinit var apiManager: ApiManager
    lateinit var musicManager: MusicManager

    override fun onCreate() {
        super.onCreate()

        apiManager = ApiManager(this)
        musicManager = MusicManager(this)
    }
}