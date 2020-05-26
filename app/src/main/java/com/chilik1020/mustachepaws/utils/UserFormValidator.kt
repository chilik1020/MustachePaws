package com.chilik1020.mustachepaws.utils

import android.text.TextUtils

fun checkUsernameInLoginForm(username: String) : String? {
    if(TextUtils.isEmpty(username))
        return "Имя пользователя не может быть пустым"

    return null
}

fun checkPasswordInLoginForm(password: String) : String? {
    if(TextUtils.isEmpty(password))
        return "Пароль не может быть пустым"

    return null
}

fun checkUsernameInSignUpForm(username: String) : String? {
    TODO()
}

fun checkEmailInSignUpForm(email: String) : String? {
    TODO()
}

fun checkPasswordInSignUpForm(password: String) : String? {
    TODO()
}

fun checkConfirmPasswordInSignUpForm(password: String, confirm: String) : String? {
    TODO()
}

