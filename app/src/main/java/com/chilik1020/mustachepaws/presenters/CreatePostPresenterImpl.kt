package com.chilik1020.mustachepaws.presenters

import com.chilik1020.mustachepaws.interactors.CreatePostInteractor
import com.chilik1020.mustachepaws.models.data.PostVO
import com.chilik1020.mustachepaws.utils.APPSCOPE
import com.chilik1020.mustachepaws.views.CreatePostView
import com.chilik1020.mustachepaws.viewstates.CreatePostViewState
import moxy.InjectViewState
import moxy.MvpPresenter
import toothpick.ktp.KTP
import javax.inject.Inject

@InjectViewState
class CreatePostPresenterImpl : MvpPresenter<CreatePostView>(),
    CreatePostInteractor.OnPostCreatedListener {

    @Inject
    lateinit var interactor: CreatePostInteractor

    init {
        KTP.openScope(APPSCOPE).inject(this)
    }

    fun createPost(description: String, image: String) {
        viewState.render(CreatePostViewState.PostCreateLoadingState)
        interactor.createPost(description, image, this)
    }

    override fun onSuccess(post: PostVO) {
        viewState.render(CreatePostViewState.PostCreatedState)
    }

    override fun onError(error: String) {
        viewState.render(CreatePostViewState.PostCreateErrorState(error))
    }
}