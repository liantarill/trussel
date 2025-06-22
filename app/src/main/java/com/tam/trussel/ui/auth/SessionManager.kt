package com.tam.trussel.ui.auth

import android.content.Context
import android.content.SharedPreferences
import com.tam.trussel.User

class SessionManager(private val context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPref.edit()

    fun saveLoginSession(user: User) {
        editor.putString("username", user.username)
        editor.putString("email", user.email)
        editor.putString("phone", user.phone)
        editor.putString("password", user.password)
        editor.putBoolean("is_logged_in", true)
        editor.putBoolean("is_verified", user.isVerified)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPref.getBoolean("is_logged_in", false)
    }

    fun getLoggedInUser(): User? {
        return if (isLoggedIn()) {
            User(
                email = sharedPref.getString("email", "") ?: "",
                password = sharedPref.getString("password", "") ?: "",
                username = sharedPref.getString("username", "") ?: "",
                phone = sharedPref.getString("phone", "") ?: "",
                isVerified = sharedPref.getBoolean("is_verified", false)
            )
        } else {
            null
        }
    }

    fun logout() {
//        editor.clear()
//        editor.apply()
        editor.putBoolean("is_logged_in", false) // Hanya set status login ke false
        editor.apply()
    }

    fun getUserEmail(): String {
        return sharedPref.getString("email", "") ?: ""
    }

    fun getUserName(): String {
        return sharedPref.getString("username", "") ?: ""
    }
}