package com.example.spiice

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "create table $TABLE_CONTACTS($KEY_ID integer primary key,$KEY_MAIL text,$KEY_TITLE text,$KEY_MESSAGE text,$KEY_DATE text)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists $TABLE_CONTACTS")
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "contactDb"
        const val TABLE_CONTACTS = "contacts"
        const val KEY_ID = "_id"
        const val KEY_MAIL = "mail"
        const val KEY_TITLE = "title"
        const val KEY_MESSAGE = "message"
        const val KEY_DATE = "date"
    }

    fun getDate(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM $TABLE_CONTACTS", null)

    }

    fun delete(id: Int){
        val db = this.writableDatabase
        db.delete(TABLE_CONTACTS, "$KEY_ID=$id", null)
        db.close()
    }
}