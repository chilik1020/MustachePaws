
package com.chilik1020.mustachepaws.presenters

import com.chilik1020.mustachepaws.interactors.FetchPostsInteractor
import com.chilik1020.mustachepaws.models.data.PostListVO
import com.chilik1020.mustachepaws.utils.APPSCOPE
import com.chilik1020.mustachepaws.views.PostListView
import com.chilik1020.mustachepaws.viewstates.PostListViewState
import moxy.InjectViewState
import moxy.MvpPresenter
import toothpick.ktp.KTP
import javax.inject.Inject

@InjectViewState
class PostListPresenterImpl : MvpPresenter<PostListView>(),
    FetchPostsInteractor.OnFetchFinishedListener {

    @Inject
    lateinit var interactor: FetchPostsInteractor

    init {
        KTP.openScope(APPSCOPE).inject(this)
    }

    fun fetchPosts() {
        viewState.render(PostListViewState.PostListLoadingState)
        interactor.fetchPosts(this)
    }

    override fun onSuccess(data: PostListVO) {
        viewState.render(PostListViewState.PostListLoadedState(posts = data.posts))
    }

    override fun onError() {
        viewState.render(PostListViewState.PostListErrorState("Couldn't retrieve data"))
    }
}