package com.chilik1020.mustachepaws.interactors

import com.chilik1020.mustachepaws.models.repository.PostRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchPostsInteractorImpl : FetchPostsInteractor {

    @Inject
    lateinit var repository: PostRepository

    override fun fetchPosts(listener: FetchPostsInteractor.OnFetchFinishedListener) {
        val subscribe = repository.fetchPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {listener.onSuccess(it)},
                {listener.onError()})
    }
}