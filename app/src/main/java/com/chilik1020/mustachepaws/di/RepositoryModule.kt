package com.chilik1020.mustachepaws.di

import com.chilik1020.mustachepaws.models.repository.PostRepositoryImpl
import com.chilik1020.mustachepaws.models.repository.PostRepository
import com.chilik1020.mustachepaws.models.repository.UserRepository
import com.chilik1020.mustachepaws.models.repository.UserRepositoryImpl
import toothpick.config.Module
import javax.inject.Singleton

@Singleton
class RepositoryModule : Module() {

    init {
        bind(PostRepository::class.java).to(PostRepositoryImpl::class.java)
        bind(UserRepository::class.java).to(UserRepositoryImpl::class.java)
    }
}