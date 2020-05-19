package com.chilik1020.mustachepaws.presenters

import com.chilik1020.mustachepaws.interactors.AuthInteractor
import com.chilik1020.mustachepaws.interactors.LoginInteractor
import com.chilik1020.mustachepaws.models.local.AppPreferences
import com.chilik1020.mustachepaws.utils.APPSCOPE
import com.chilik1020.mustachepaws.views.LoginView
import com.chilik1020.mustachepaws.viewstates.LoginViewState
import moxy.InjectViewState
import moxy.MvpPresenter
import toothpick.ktp.KTP
import javax.inject.Inject

@InjectViewState
class LoginPresenterImpl : MvpPresenter<LoginView>(),LoginPresenter,
    AuthInteractor.OnAuthFinishedListener, LoginInteractor.OnDetailsRetrievalFinishedListener{

    @Inject
    lateinit var interactor: LoginInteractor

    @Inject
    lateinit var preferences: AppPreferences

    init {
        KTP.openScope(APPSCOPE).inject(this)
    }

    override fun executeLogin(username: String, password: String) {
        viewState.render(LoginViewState.LoginLoadingState)
        interactor.login(username, password, this)
    }

    override fun onAuthSuccess() {
        interactor.persistAccessToken(preferences)
        interactor.retrieveDetails(preferences, this)
    }

    override fun onAuthError() {
        viewState.render(LoginViewState.LoginErrorState("Authentication error"))
    }

    override fun onDetailsRetrievalSuccess() {
        interactor.persistUserDetails(preferences)
        viewState.render(LoginViewState.LoggedState)
    }

    override fun onDetailsRetrievalError() {
        interactor.retrieveDetails(preferences, this)
    }
}