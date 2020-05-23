package com.chilik1020.mustachepaws.interactors

import com.chilik1020.mustachepaws.models.data.PostVO
import com.chilik1020.mustachepaws.models.repository.PostRepository
import javax.inject.Inject

class CreatePostInteractorImpl : CreatePostInteractor {

    @Inject
    lateinit var repository: PostRepository

    override fun createPost(
        description: String,
        image: String,
        listener: CreatePostInteractor.OnPostCreatedListener
    ): PostVO {
        TODO("Not yet implemented")
    }
}