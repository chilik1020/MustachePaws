package com.chilik1020.mustachepaws.viewstates

sealed class CreatePostViewState {
    object PostCreateLoadingState : CreatePostViewState()
    object PostCreatedState : CreatePostViewState()
    class PostCreateErrorState(val message: String) : CreatePostViewState()
}