package com.chilik1020.mustachepaws.di

import com.chilik1020.mustachepaws.interactors.LoginInteractor
import com.chilik1020.mustachepaws.interactors.LoginInteractorImpl
import com.chilik1020.mustachepaws.interactors.SignUpInteractor
import com.chilik1020.mustachepaws.interactors.SignUpInteractorImpl
import com.chilik1020.mustachepaws.presenters.LoginPresenter
import com.chilik1020.mustachepaws.presenters.LoginPresenterImpl
import toothpick.config.Module
import javax.inject.Singleton

@Singleton
class InteractorModule : Module() {

    init {
        bind(LoginInteractor::class.java).to(LoginInteractorImpl::class.java)
        bind(LoginPresenter::class.java).to(LoginPresenterImpl::class.java)

        bind(SignUpInteractor::class.java).to(SignUpInteractorImpl::class.java)
    }
}