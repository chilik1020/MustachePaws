package com.chilik1020.mustachepaws.models.local

import android.content.SharedPreferences
import com.chilik1020.mustachepaws.models.data.UserVO
import javax.inject.Inject

class AppPreferences @Inject constructor(val preferences: SharedPreferences) {

    val accessToken: String?
        get() = preferences.getString("ACCESS_TOKEN", null)

    fun storeAccessToken(accessToken: String) {
        preferences.edit().putString("ACCESS_TOKEN", accessToken).apply()
    }

    val userDetails: UserVO
        get() {
            return UserVO(
                preferences.getLong("ID", 0),
                preferences.getString("USERNAME", null),
                preferences.getString("FIRSTNAME", null),
                preferences.getString("LASTNAME", null),
                preferences.getString("EMAIL", null),
                preferences.getString("PHONENUMBER", null),
                preferences.getString("CREATED_AT", null)
            )
        }

    fun storeUserDetails(user: UserVO) {
        val editor: SharedPreferences.Editor = preferences.edit()

        editor.putLong("ID", user.id).apply()
        editor.putString("USERNAME", user.username).apply()
        editor.putString("FIRSTNAME", user.firstname).apply()
        editor.putString("LASTNAME", user.lastname).apply()
        editor.putString("EMAIL", user.email).apply()
        editor.putString("PHONENUMBER", user.phoneNumber).apply()
        editor.putString("CREATED_AT", user.createdAt).apply()
    }

    fun clear() {
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }
}