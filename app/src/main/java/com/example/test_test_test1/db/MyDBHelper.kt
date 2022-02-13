package com.example.test_test_test1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File

class MyDBHelper(context: Context): SQLiteOpenHelper(
        context,
        MyDBNameClass.DATABASE_NAME,
        null,
        MyDBNameClass.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
//        db?.execSQL(MyDBNameClass.CREATE_TABLE)

    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        val my_db = File(MyDBNameClass.myPath)
        if (my_db.exists()) {
            Log.d("MyLog", "Наша БД открылась, ура")
            val db = SQLiteDatabase.openDatabase(MyDBNameClass.myPath, null, SQLiteDatabase.OPEN_READONLY)
        } else Log.d("MyLog", "БД не найдена, увы")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(MyDBNameClass.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    // выбираем бренд, где год уже выбран и равен data
    fun sql_choice_year(db: SQLiteDatabase?){
        db?.execSQL(MyDBNameClass.SQL_CHOICE_YEAR)
    }
}