package com.chilik1020.mustachepaws.utils

const val USERNAME_LENGTH_PATTERN = """.{3,10}"""
const val USERNAME_SYMBOL_PATTERN = """^(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$"""

const val EMAIL_PATTERN = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
        "\\@" +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
        "(" +
        "\\." +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
        ")+"

const val PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,}$"

fun checkUsernameInLoginForm(username: String) : String? {
    if(username.isEmpty())
        return "Имя пользователя не может быть пустым"

    return null
}

fun checkPasswordInLoginForm(password: String) : String? {
    if(password.isEmpty())
        return "Пароль не может быть пустым"

    return null
}

fun checkUsernameInSignUpForm(username: String) : String? {
    val regexLength = Regex(USERNAME_LENGTH_PATTERN)
    if(!regexLength.matches(username))
        return "Имя должно состоять из 3-10 символов"

    val regex = Regex(USERNAME_SYMBOL_PATTERN)
    if(!regex.matches(username))
        return "Допустимые символы \"Aa-Zz, . , _ \""

    return null
}

fun checkEmailInSignUpForm(email: String) : String? {
    val regexEmail = Regex(EMAIL_PATTERN)
    if(!regexEmail.matches(email))
        return "Неверный формат электронной почты"

    return null
}

fun checkPasswordInSignUpForm(password: String) : String? {
    val regexPassword = Regex(PASSWORD_PATTERN)
    if(!regexPassword.matches(password))
        return "Минимум 6 символов, одна заглавная буква, одна строчная, одна цифра"

    return null
}

fun checkConfirmPasswordInSignUpForm(password: String, confirm: String) : String? {
    if (password != confirm)
        return "Пароли не совпадают"

    return null
}

