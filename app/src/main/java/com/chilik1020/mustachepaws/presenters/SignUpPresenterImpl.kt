package com.chilik1020.mustachepaws.presenters

import com.chilik1020.mustachepaws.interactors.AuthInteractor
import com.chilik1020.mustachepaws.interactors.SignUpInteractor
import com.chilik1020.mustachepaws.models.data.UserRequestObject
import com.chilik1020.mustachepaws.models.local.AppPreferences
import com.chilik1020.mustachepaws.utils.APPSCOPE
import com.chilik1020.mustachepaws.views.SignUpView
import com.chilik1020.mustachepaws.viewstates.SignUpViewState
import moxy.InjectViewState
import moxy.MvpPresenter
import toothpick.ktp.KTP
import javax.inject.Inject

@InjectViewState
class SignUpPresenterImpl : MvpPresenter<SignUpView>(), SignUpPresenter,
    SignUpInteractor.OnSignUpFinishedListener, AuthInteractor.OnAuthFinishedListener{

    @Inject
    lateinit var interactor: SignUpInteractor

    @Inject
    lateinit var preferences: AppPreferences

    init {
        KTP.openScope(APPSCOPE).inject(this)
    }

    override fun executeSignUp(user: UserRequestObject) {
        viewState.render(SignUpViewState.SignUpLoadingState)
        interactor.signUp(user, this)
    }

    override fun onSuccess() {
        interactor.getAuthorization(this)
    }

    override fun onAuthSuccess() {
        interactor.persistAccessToken(preferences)
        interactor.persistUserDetails(preferences)
        viewState.render(SignUpViewState.SignUpFinishedState)
    }

    override fun onAuthError(message: String) {
        viewState.render(SignUpViewState.SignUpErrorState(message))
    }

    override fun onError(message: String) {
        viewState.render(SignUpViewState.SignUpErrorState(message))
    }
}