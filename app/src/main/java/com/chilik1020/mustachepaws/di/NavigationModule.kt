package com.chilik1020.mustachepaws.di

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module
import javax.inject.Singleton

@Singleton
class NavigationModule : Module() {

    init {
        val cicerone: Cicerone<Router> = Cicerone.create()

        val router: Router = cicerone.router
        val navigationHolder: NavigatorHolder =  cicerone.navigatorHolder

        bind(Router::class.java).toInstance(router)
        bind(NavigatorHolder::class.java).toInstance(navigationHolder)
    }
}