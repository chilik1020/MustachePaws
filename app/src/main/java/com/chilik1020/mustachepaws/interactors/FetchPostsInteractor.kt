package com.chilik1020.mustachepaws.interactors

import com.chilik1020.mustachepaws.models.data.PostListVO

interface FetchPostsInteractor {

    interface OnFetchFinishedListener {
        fun onSuccess(data: PostListVO)
        fun onError()
    }

    fun fetchPosts(listener: FetchPostsInteractor.OnFetchFinishedListener)
}