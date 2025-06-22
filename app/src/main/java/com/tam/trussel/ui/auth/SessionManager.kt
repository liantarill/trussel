package com.tam.trussel.ui.auth

import android.content.Context
import com.tam.trussel.User

class SessionManager(private val context: Context) {
    private val sharedPref = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    fun saveLoginSession(user: User) {
        editor.putString("email", user.email)
        editor.putString("password", user.password)
        editor.putBoolean("is_logged_in", true)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPref.getBoolean("is_logged_in", false)
    }

    fun getLoggedInUser(): User? {
        return if (isLoggedIn()) {
            User(
                sharedPref.getString("email", "") ?: "",
                sharedPref.getString("password", "") ?: ""
            )
        } else {
            null
        }
    }

    fun logout() {
        editor.clear()
        editor.apply()
    }
}