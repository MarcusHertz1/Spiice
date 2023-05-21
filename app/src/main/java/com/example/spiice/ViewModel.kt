package com.example.spiice

import android.app.Application
import android.content.ContentValues
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spiice.adapter.AdapterItem
import java.text.SimpleDateFormat
import java.util.*

class ViewModel(app: Application) : AndroidViewModel(app) {
    companion object {
        const val NO_EMAIL = "NO_EMAIL"
        const val BAD_PASSWORD = "BAD_PASSWORD"
        const val CORRECT = "CORRECT"
    }

    var deletedItemId = -1
    var showingTitle = ""
    var showingMessage = ""

    private val updateRecyclerViewMutableLiveData = MutableLiveData<Unit>()
    val updateRecyclerViewLiveData: LiveData<Unit> = updateRecyclerViewMutableLiveData

    fun updateRecyclerView() = updateRecyclerViewMutableLiveData.postValue(Unit)

    fun checkEmail(email: String): Boolean {
        return email.matches("[A-Za-z\\d._%+-]+@[A-Za-z\\d.-]+\\.[A-Za-z]{2,6}".toRegex())
    }

    fun checkPassword(password: String): Boolean {
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=]).{6,50}\$".toRegex())
    }

    fun checkName(name: String): Boolean {
        return name.length in 3..255
    }

    fun getDBArray(context: Context): MutableList<AdapterItem> {
        val result = mutableListOf<AdapterItem>()
        val dbHelper = DBHelper(context)
        val cursor = dbHelper.getDate()
        cursor?.apply {
            moveToFirst()
            while (!cursor.isAfterLast) {
                val emailIndex = getColumnIndex(DBHelper.KEY_MAIL)
                if (emailIndex >= 0 && getString(emailIndex) == SharedPreferences.getLoggedEmail()) {
                    val idIndex = getColumnIndex(DBHelper.KEY_ID)
                    val titleIndex = getColumnIndex(DBHelper.KEY_TITLE)
                    val messageIndex = getColumnIndex(DBHelper.KEY_MESSAGE)
                    val dateIndex = getColumnIndex(DBHelper.KEY_DATE)
                    val item = AdapterItem()
                    if (titleIndex >= 0) {
                        item.title = getString(titleIndex)
                    }
                    if (messageIndex >= 0) {
                        item.message = getString(messageIndex)
                    }
                    if (dateIndex >= 0) {
                        item.date = getString(dateIndex)
                    }
                    if (idIndex >= 0) {
                        item.id = getInt(idIndex)
                    }
                    result.add(item)
                }
                cursor.moveToNext()
            }
        }
        return result
    }

    fun setDBDate(context: Context, title: String, message: String) {
        val dbHelper = DBHelper(context)
        val database = dbHelper.writableDatabase
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val current = formatter.format(Calendar.getInstance().time)
        val contentValues = ContentValues()
        contentValues.put(DBHelper.KEY_MAIL, SharedPreferences.getLoggedEmail())
        contentValues.put(DBHelper.KEY_TITLE, title)
        contentValues.put(DBHelper.KEY_MESSAGE, message)
        contentValues.put(DBHelper.KEY_DATE, current)
        database.insert(DBHelper.TABLE_CONTACTS, null, contentValues)
    }

    fun checkEmailPassword(email: String, password: String): String {
        SharedPreferences.getEmailsPasswords()?.apply {
            this.split("?").forEach {
                if (it.substringBefore("!") == email) {
                    return if (it.substringAfter("!") == password) {
                        CORRECT
                    } else BAD_PASSWORD
                }
            }
        }
        return NO_EMAIL
    }

    fun checkEmailExists(email: String): Boolean {
        SharedPreferences.getEmailsPasswords()?.apply {
            this.split("?").forEach {
                if (it.substringBefore("!") == email) {
                    return true
                }
            }
        }
        return false
    }
}