package com.chilik1020.mustachepaws.di

import com.chilik1020.mustachepaws.models.repository.PostRepositoryImpl
import com.chilik1020.mustachepaws.models.repository.PostRepository
import toothpick.config.Module
import javax.inject.Singleton

@Singleton
class RepositoryModule : Module() {

    init {
        bind(PostRepository::class.java).to(PostRepositoryImpl::class.java)
    }
}