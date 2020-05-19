package com.chilik1020.mustachepaws.views

import com.chilik1020.mustachepaws.viewstates.SignUpViewState
import moxy.MvpView

interface SignUpView : MvpView {

    fun render(state: SignUpViewState)
}