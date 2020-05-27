package com.chilik1020.mustachepaws.utils

import com.chilik1020.mustachepaws.utils.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class UserFormValidatorKtTest {

    @Test
    fun checkUsernameInLoginFormTest() {
        assertNull(checkUsernameInLoginForm("Chilik"))
        assertNull(checkUsernameInLoginForm("C"))

        assertNotNull(checkUsernameInLoginForm(""))
    }

    @Test
    fun checkPasswordInLoginFormTest() {
        assertNull(checkPasswordInLoginForm("password"))
        assertNull(checkPasswordInLoginForm("p"))

        assertNotNull(checkPasswordInLoginForm(""))
    }

    @Test
    fun checkUsernameInSignUpFormTest() {
        assertNull(checkUsernameInSignUpForm("Chilik"))
        assertNull(checkUsernameInSignUpForm("4lk"))
        assertNull(checkUsernameInSignUpForm("4lK_3lk"))
        assertNull(checkUsernameInSignUpForm("4444444444"))

        assertNotNull("_4lk")
        assertNotNull("4lk_")
        assertNotNull(".4lk")
        assertNotNull("?4lk")
        assertNotNull("4")
        assertNotNull("er")
    }

    @Test
    fun checkEmailInSignUpFormTest() {
        assertNull(checkEmailInSignUpForm("av@gmail.com"))
        assertNull(checkEmailInSignUpForm("a@g.c"))
        assertNull(checkEmailInSignUpForm("Av@g.com"))

        assertNotNull(checkEmailInSignUpForm("vg.com"))
        assertNotNull(checkEmailInSignUpForm("vg@.com"))
        assertNotNull(checkEmailInSignUpForm("vg@com"))
        assertNotNull(checkEmailInSignUpForm("v"))
    }

    @Test
    fun checkPasswordInSignUpFormTest() {
        assertNull(checkPasswordInSignUpForm("Dref2d"))
        assertNull(checkPasswordInSignUpForm("De2???2"))
        assertNull(checkPasswordInSignUpForm("De2...2"))
        assertNull(checkPasswordInSignUpForm("De21!!!!2"))

        assertNotNull(checkPasswordInSignUpForm("dref2d"))
        assertNotNull(checkPasswordInSignUpForm("Drefsd"))
        assertNotNull(checkPasswordInSignUpForm("DDDD2D"))
        assertNotNull(checkPasswordInSignUpForm("Dref"))
        assertNotNull(checkPasswordInSignUpForm("222222"))
        assertNotNull(checkPasswordInSignUpForm("eeeeee"))
    }

    @Test
    fun checkConfirmPasswordInSignUpFormTest() {
        assertNull(checkConfirmPasswordInSignUpForm("Erf4er", "Erf4er"))

        assertNotNull(checkConfirmPasswordInSignUpForm("Erf4er", "erf4er"))
        assertNotNull(checkConfirmPasswordInSignUpForm("Erf4er", ""))
        assertNotNull(checkConfirmPasswordInSignUpForm("", "Erf4er"))
    }
}