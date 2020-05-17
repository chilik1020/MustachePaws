
package com.chilik1020.mustachepaws.presenters

import com.chilik1020.mustachepaws.models.repository.PostRepository
import com.chilik1020.mustachepaws.utils.APPSCOPE
import com.chilik1020.mustachepaws.views.PostListView
import com.chilik1020.mustachepaws.viewstates.PostListViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import toothpick.ktp.KTP
import javax.inject.Inject

@InjectViewState
class PostListPresenter : MvpPresenter<PostListView>() {

    @Inject
    lateinit var postRepository: PostRepository

    init {
        KTP.openScope(APPSCOPE).inject(this)
    }

    fun fetchPosts() {
        viewState.render(PostListViewState.PostListLoadingState)
        val subscription =  postRepository.fetchPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.render(PostListViewState.PostListLoadingState) }
            .subscribe({
                        viewState.render(PostListViewState.PostListLoadedState(posts = it.posts))},

                        {viewState.render(PostListViewState.PostListErrorState(message = it?.localizedMessage.toString()))})

    }
}