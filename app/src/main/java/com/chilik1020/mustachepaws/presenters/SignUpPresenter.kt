package com.chilik1020.mustachepaws.presenters

import com.chilik1020.mustachepaws.models.data.UserRequestObject

interface SignUpPresenter {
    fun executeSignUp(user: UserRequestObject)
}