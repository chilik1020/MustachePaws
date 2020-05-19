package com.chilik1020.mustachepaws.viewstates

sealed class SignUpViewState {
    object SignUpLoadingState : SignUpViewState()
    object SignUpFinishedState : SignUpViewState()
    class SignUpErrorState(val message: String) : SignUpViewState()
}