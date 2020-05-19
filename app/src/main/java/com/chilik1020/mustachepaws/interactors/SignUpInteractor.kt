package com.chilik1020.mustachepaws.interactors

import com.chilik1020.mustachepaws.models.data.UserRequestObject

interface SignUpInteractor : AuthInteractor {

    interface OnSignUpFinishedListener {
        fun onSuccess()
        fun onError(message: String)
    }

    fun signUp(user: UserRequestObject, listener: OnSignUpFinishedListener)

    fun getAuthorization(listener: AuthInteractor.OnAuthFinishedListener)
}