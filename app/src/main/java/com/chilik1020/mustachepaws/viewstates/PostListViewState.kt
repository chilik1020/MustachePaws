package com.chilik1020.mustachepaws.viewstates

import com.chilik1020.mustachepaws.models.data.PostVO

sealed class PostListViewState {
    object PostListLoadingState : PostListViewState()
    class PostListLoadedState(val posts: List<PostVO>) : PostListViewState()
    class PostListErrorState(val message: String) : PostListViewState()
}