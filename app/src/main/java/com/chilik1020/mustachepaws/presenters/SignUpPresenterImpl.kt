package com.chilik1020.mustachepaws.presenters

import com.chilik1020.mustachepaws.interactors.AuthInteractor
import com.chilik1020.mustachepaws.interactors.SignUpInteractor
import com.chilik1020.mustachepaws.models.data.UserRequestObject
import com.chilik1020.mustachepaws.models.local.AppPreferences
import com.chilik1020.mustachepaws.utils.*
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

    override fun executeSignUp(username: String, email: String, password: String, confirmPassword: String) {

        if(!isSignUpFormCorrect(username, email, password, confirmPassword))
            return

        viewState.render(SignUpViewState.SignUpLoadingState)
        interactor.signUp(UserRequestObject(username, "", "", email, "", password), this)
    }

    private fun isSignUpFormCorrect(username: String, email: String, password: String, confirmPassword: String): Boolean {
        val usernameErrorMessage = checkUsernameInSignUpForm(username)
        if (usernameErrorMessage != null) {
            viewState.render(SignUpViewState.UsernameErrorState(usernameErrorMessage))
            return false
        }

        val emailErrorMessage = checkEmailInSignUpForm(email)
        if (emailErrorMessage != null) {
            viewState.render(SignUpViewState.EmailErrorState(emailErrorMessage))
            return false
        }

        val passwordErrorMessage = checkPasswordInSignUpForm(password)
        if(passwordErrorMessage != null) {
            viewState.render(SignUpViewState.PasswordErrorState(passwordErrorMessage))
            return false
        }

        val confirmPasswordErrorMessage = checkConfirmPasswordInSignUpForm(password, confirmPassword)
        if (confirmPasswordErrorMessage != null) {
            viewState.render(SignUpViewState.ConfirmPasswordErrorState(confirmPasswordErrorMessage))
            return false
        }

        return true
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