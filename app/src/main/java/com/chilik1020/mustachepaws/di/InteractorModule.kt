package com.chilik1020.mustachepaws.di

import com.chilik1020.mustachepaws.interactors.*
import com.chilik1020.mustachepaws.presenters.LoginPresenter
import com.chilik1020.mustachepaws.presenters.LoginPresenterImpl
import toothpick.config.Module
import javax.inject.Singleton

@Singleton
class InteractorModule : Module() {

    init {
        bind(LoginInteractor::class.java).to(LoginInteractorImpl::class.java)
        bind(SignUpInteractor::class.java).to(SignUpInteractorImpl::class.java)
        bind(FetchPostsInteractor::class.java).to(FetchPostsInteractorImpl::class.java)
        bind(CreatePostInteractor::class.java).to(CreatePostInteractorImpl::class.java)
    }
}