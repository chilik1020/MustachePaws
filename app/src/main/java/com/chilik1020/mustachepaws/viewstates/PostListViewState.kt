package com.chilik1020.mustachepaws.viewstates

import com.chilik1020.mustachepaws.models.data.Post

sealed class PostListViewState {
    object PostListLoadingState : PostListViewState()
    class PostListLoadedState(val posts: List<Post>) : PostListViewState()
    class PostListErrorState(val message: String) : PostListViewState()
}