package com.chilik1020.mustachepaws.interactors

import com.chilik1020.mustachepaws.models.data.LoginRequestObject
import com.chilik1020.mustachepaws.models.data.UserVO
import com.chilik1020.mustachepaws.models.local.AppPreferences
import com.chilik1020.mustachepaws.models.remote.MustachePawsApi
import com.chilik1020.mustachepaws.models.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginInteractorImpl : LoginInteractor {

    @Inject
    lateinit var service: MustachePawsApi

    @Inject
    lateinit var userRepository: UserRepository

    override lateinit var userDetails: UserVO
    override lateinit var accessToken: String
    override lateinit var submittedUsername: String
    override lateinit var submittedPassword: String

    override fun login(username: String, password: String,
        listener: AuthInteractor.OnAuthFinishedListener) {
        submittedUsername = username
        submittedPassword = password
        val loginRequestObject = LoginRequestObject(username, password)
        val subscribe = service.login(loginRequestObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                if (res.code() != 403) {
                    accessToken = res.headers()["Authorization"] as String
                    listener.onAuthSuccess()
                } else
                    listener.onAuthError()
            },
                { error ->
                    listener.onAuthError()
                    error.printStackTrace()
                })
    }

    override fun retrieveDetails(preferences: AppPreferences,
        listener: LoginInteractor.OnDetailsRetrievalFinishedListener
    ) {
        val disposable = userRepository.echoDetails()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                userDetails = res
                listener.onDetailsRetrievalSuccess()
            },
                { error ->
                    listener.onDetailsRetrievalError()
                    error.printStackTrace()
                })
    }

    override fun persistAccessToken(preferences: AppPreferences) {
        preferences.storeAccessToken(accessToken)
    }

    override fun persistUserDetails(preferences: AppPreferences) {
        preferences.storeUserDetails(userDetails)
    }
}