package com.example.spiice

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object SharedPreferences {
    private lateinit var sharedPref: SharedPreferences

    fun init(context: Context) {
        sharedPref = context.getSharedPreferences("application", MODE_PRIVATE)
    }

    private const val EMAILS_AND_PASSWORDS = "EMAILS_AND_PASSWORDS"
    private const val LOGGED_EMAIL = "LOGGED_EMAIL"

    fun setEmailsPasswords(email: String, password: String) {
        val editor = sharedPref.edit()
        editor.putString(EMAILS_AND_PASSWORDS, getEmailsPasswords() + "$email!$password?")
        editor.apply()
    }

    fun getEmailsPasswords() = sharedPref.getString(EMAILS_AND_PASSWORDS, "")

    fun setLoggedEmail(email: String) {
        val editor = sharedPref.edit()
        editor.putString(LOGGED_EMAIL, email)
        editor.apply()
    }

    fun getLoggedEmail() = sharedPref.getString(LOGGED_EMAIL, "")

}