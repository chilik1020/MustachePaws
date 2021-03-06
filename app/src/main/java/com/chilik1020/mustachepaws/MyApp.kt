package com.chilik1020.mustachepaws

import android.app.Application
import com.chilik1020.mustachepaws.di.*
import com.chilik1020.mustachepaws.utils.APPSCOPE
import toothpick.ktp.KTP
import toothpick.configuration.Configuration

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initToothPick()
    }

    private fun initToothPick() {
        if (BuildConfig.DEBUG) {
            KTP.setConfiguration(Configuration.forDevelopment())
            KTP.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        }

        KTP.openScope(APPSCOPE)
            .installModules(
                AppModule(this),
                NavigationModule(),
                NetworkModule(),
                RepositoryModule(),
                InteractorModule())
    }

    override fun onTerminate() {
        super.onTerminate()
        KTP.closeScope(APPSCOPE)
    }
}