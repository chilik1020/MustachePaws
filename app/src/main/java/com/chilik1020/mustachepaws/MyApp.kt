package com.chilik1020.mustachepaws

import android.app.Application
import com.chilik1020.mustachepaws.di.AppModule
import com.chilik1020.mustachepaws.di.InteractorModule
import com.chilik1020.mustachepaws.di.RepositoryModule
import com.chilik1020.mustachepaws.utils.APPSCOPE
import toothpick.ktp.KTP
import toothpick.configuration.Configuration

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeToothPick()
    }

    private fun initializeToothPick() {
        if (BuildConfig.DEBUG) {
            KTP.setConfiguration(Configuration.forDevelopment())
            KTP.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        }

        KTP.openScope(APPSCOPE)
            .installModules(
                AppModule(this),
                RepositoryModule(),
                InteractorModule())
    }

    override fun onTerminate() {
        super.onTerminate()
        KTP.closeScope(APPSCOPE)
    }
}