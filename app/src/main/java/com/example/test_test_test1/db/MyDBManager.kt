package com.example.test_test_test1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.test_test_test1.MyDBNameClass.data

class MyDBManager(context: Context) {
    val myDBHelper = MyDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB() {
        db = myDBHelper.readableDatabase
    }

    /*fun insertToDB(title: String, content: String) {
        val values = ContentValues().apply {
            put(MyDBNameClass.COLUMN_NAME_TITLE, title)
            put(MyDBNameClass.COLUMN_NAME_CONTENT, content)
        }
        db?.insert(MyDBNameClass.TABLE_NAME, null, values)
    }*/

    fun readDBDataBrand(): ArrayList<String> {
        val dataList = ArrayList<String>()
        val cursor = db?.query(
                MyDBNameClass.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null)

        while (cursor?.moveToNext()!!) {
            val brandText = cursor.getString(cursor.getColumnIndex(MyDBNameClass.KEY_BRAND))
            dataList.add(brandText.toString())
        }
// выбираем бренд, где год уже выбран и равен data
        if (data != ""){
            myDBHelper.sql_choice_year(db)
        }
        cursor.close()
        return dataList
    }
    fun readDBDataModel(): ArrayList<String> {
        val dataList = ArrayList<String>()
        val cursor = db?.query(
                MyDBNameClass.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null)

        while (cursor?.moveToNext()!!) {
            val modelText = cursor.getString(cursor.getColumnIndex(MyDBNameClass.KEY_MODEL))
            dataList.add(modelText.toString())
        }
        cursor.close()
        return dataList
    }
    fun readDBDataYear(): ArrayList<String> {
        val dataList = ArrayList<String>()
        val cursor = db?.query(
                MyDBNameClass.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null)

        while (cursor?.moveToNext()!!) {
            val yearText = cursor.getString(cursor.getColumnIndex(MyDBNameClass.KEY_YEAR))
            dataList.add(yearText.toString())
        }
        cursor.close()
        return dataList
    }


    fun closeDB(){
        myDBHelper.close()
    }
}