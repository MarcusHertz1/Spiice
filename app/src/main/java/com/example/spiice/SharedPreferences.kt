package com.example.spiice

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


object SharedPreferences {
    private lateinit var appContext: Context
    private lateinit var sharedPref: SharedPreferences

    fun init(context: Context) {
        appContext = context
        sharedPref = appContext.getSharedPreferences("application", MODE_PRIVATE)
    }

    private const val EMAILS_AND_PASSWORDS = "EMAILS_AND_PASSWORDS"
    private const val LOGGED_EMAIL = "LOGGED_EMAIL"

    fun setEmailsPasswords(email: String, password: String) {
        val editor = sharedPref.edit()
        editor.putString(EMAILS_AND_PASSWORDS, getEmailsPasswords() + "$email!$password?")
        editor.apply()
    }

    private fun getEmailsPasswords() = sharedPref.getString(EMAILS_AND_PASSWORDS, "")

    fun setLoggedEmail(email: String) {
        val editor = sharedPref.edit()
        editor.putString(LOGGED_EMAIL, email)
        editor.apply()
    }

    fun getLoggedEmail() = sharedPref.getString(LOGGED_EMAIL, "")









    const val NO_EMAIL = "NO_EMAIL"
    const val BAD_PASSWORD = "BAD_PASSWORD"
    const val CORRECT = "CORRECT"

    fun checkEmailPassword(email: String, password: String): String{
        getEmailsPasswords()?. apply {
            this.split("?").forEach {
                if (it.substringBefore("!")==email) {
                    return if (it.substringAfter("!")==password) {
                        CORRECT
                    } else BAD_PASSWORD
                }
            }

        }
        return NO_EMAIL
    }

    fun checkEmailExists(email: String):Boolean{
        getEmailsPasswords()?. apply {
            this.split("?").forEach {
                if (it.substringBefore("!")==email) {
                    return true
                }
            }
        }
        return false
    }
}