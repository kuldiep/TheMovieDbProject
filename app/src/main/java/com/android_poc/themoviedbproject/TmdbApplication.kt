package com.android_poc.themoviedbproject

import android.app.Application

class TmdbApplication : Application(){

    companion object {
        private lateinit var tmdbAppInstance: TmdbApplication
        fun getApplicationInstance(): TmdbApplication {
            return tmdbAppInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        tmdbAppInstance = this
    }
}