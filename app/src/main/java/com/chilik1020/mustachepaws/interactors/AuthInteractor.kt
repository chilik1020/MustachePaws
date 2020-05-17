package com.chilik1020.mustachepaws.interactors

import com.chilik1020.mustachepaws.models.data.UserVO
import com.chilik1020.mustachepaws.models.local.AppPreferences

interface AuthInteractor {

    var userDetails: UserVO
    var accessToken: String
    var submittedUsername: String
    var submittedPassword: String

    interface OnAuthFinishedListener {
        fun onAuthSuccess()
        fun onAuthError()
        fun onUsernameError()
        fun onPasswordError()
    }

    fun persistAccessToken(preferences: AppPreferences)

    fun persistUserDetails(preferences: AppPreferences)
}