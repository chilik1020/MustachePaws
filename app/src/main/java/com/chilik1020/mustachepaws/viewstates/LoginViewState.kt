package com.chilik1020.mustachepaws.viewstates

sealed class LoginViewState {

    object LoginLoadingState : LoginViewState()
    object LoggedState: LoginViewState()
    class LoginErrorState(val message : String) : LoginViewState()
}