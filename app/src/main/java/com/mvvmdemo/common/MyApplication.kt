package com.mvvmdemo.common

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {
        private var INSTANCE:MyApplication? = null
        fun getApplicationContext() : Context? {
            return INSTANCE?.applicationContext
        }
    }
    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
    }
}