package com.chilik1020.mustachepaws.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.chilik1020.mustachepaws.models.local.AppPreferences
import com.chilik1020.mustachepaws.models.remote.MustachePawsApi
import com.chilik1020.mustachepaws.utils.PREFERENCE_FILE_NAME
import toothpick.config.Module

class AppModule (application: Application): Module() {

    init {
        val context = application.applicationContext
        bind(Application::class.java).toInstance(application)
        bind(Context::class.java).toInstance(context)

        val sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, 0)
        bind(SharedPreferences::class.java).toInstance(sharedPreferences)
        bind(AppPreferences::class.java)
    }
}