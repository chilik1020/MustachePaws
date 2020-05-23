package com.chilik1020.mustachepaws.interactors

import com.chilik1020.mustachepaws.models.data.PostVO

interface CreatePostInteractor {

    interface OnPostCreatedListener {
        fun onSuccess(post: PostVO)
        fun onError()
    }

    fun createPost(description: String, image: String, listener: OnPostCreatedListener): PostVO
}