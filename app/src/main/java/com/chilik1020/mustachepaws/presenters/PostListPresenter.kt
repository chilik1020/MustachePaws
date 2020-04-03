package com.chilik1020.mustachepaws.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.chilik1020.mustachepaws.models.repository.PostReporitoryImpl
import com.chilik1020.mustachepaws.views.PostListView
import com.chilik1020.mustachepaws.viewstates.PostListViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

@InjectViewState
class PostListPresenter : MvpPresenter<PostListView>() {

    private val repository = PostReporitoryImpl()

    fun fetchPosts() {
        viewState.render(PostListViewState.PostListLoadingState)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                delay(3000)
                val posts = repository.fetchPosts()
                viewState.render(PostListViewState.PostListLoadedState(posts = posts))
            } catch (ex : Exception) {
                ex.printStackTrace()
                viewState.render(PostListViewState.PostListErrorState(message = ex.localizedMessage))
            }
        }
    }
}