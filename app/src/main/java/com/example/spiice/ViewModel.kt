package com.example.spiice

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ViewModel (app: Application) : AndroidViewModel(app) {

    fun checkEmail (email: String) : Boolean {
        return email.matches("[A-Za-z\\d._%+-]+@[A-Za-z\\d.-]+\\.[A-Za-z]{2,6}".toRegex())
    }

    fun checkPassword (password: String) : Boolean {
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=]).{6,50}\$".toRegex())
    }

    fun checkName (name: String) : Boolean {
        return name.length in 3..255
    }
}