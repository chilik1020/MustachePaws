package com.chilik1020.mustachepaws.presenters

import com.chilik1020.mustachepaws.Screens
import com.chilik1020.mustachepaws.interactors.AuthInteractor
import com.chilik1020.mustachepaws.interactors.LoginInteractor
import com.chilik1020.mustachepaws.models.local.AppPreferences
import com.chilik1020.mustachepaws.utils.APPSCOPE
import com.chilik1020.mustachepaws.utils.checkPasswordInLoginForm
import com.chilik1020.mustachepaws.utils.checkUsernameInLoginForm
import com.chilik1020.mustachepaws.views.LoginView
import com.chilik1020.mustachepaws.viewstates.LoginViewState
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import toothpick.ktp.KTP
import javax.inject.Inject

@InjectViewState
class LoginPresenterImpl : MvpPresenter<LoginView>(),LoginPresenter,
    AuthInteractor.OnAuthFinishedListener, LoginInteractor.OnDetailsRetrievalFinishedListener{

    @Inject
    lateinit var interactor: LoginInteractor

    @Inject
    lateinit var preferences: AppPreferences

    @Inject
    lateinit var router: Router

    init {
        KTP.openScope(APPSCOPE).inject(this)
    }

    override fun executeLogin(username: String, password: String) {
        if(!isLoginFormCorrect(username, password))
            return

        viewState.render(LoginViewState.LoginLoadingState)
        interactor.login(username, password, this)
    }

    private fun isLoginFormCorrect(username: String, password: String): Boolean {
        val usernameErrorMessage = checkUsernameInLoginForm(username)
        if (usernameErrorMessage != null) {
            viewState.render(LoginViewState.UsernameErrorState(usernameErrorMessage))
            return false
        }

        val passwordErrorMessage = checkPasswordInLoginForm(password)
        if (passwordErrorMessage != null) {
            viewState.render(LoginViewState.PasswordErrorState(passwordErrorMessage))
            return false
        }

        return true
    }

    override fun onAuthSuccess() {
        interactor.persistAccessToken(preferences)
        interactor.retrieveDetails(preferences, this)
    }

    override fun onAuthError(message: String) {
        viewState.render(LoginViewState.LoginErrorState(message))
    }

    override fun onDetailsRetrievalSuccess() {
        interactor.persistUserDetails(preferences)
        viewState.render(LoginViewState.LoggedState)
    }

    override fun onDetailsRetrievalError() {
        interactor.retrieveDetails(preferences, this)
    }
}