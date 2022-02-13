package com.example.test_test_test1

import android.provider.BaseColumns

// Здесь будут храниться все названия
object MyDBNameClass {
    //    const val TABLE_NAME = "my_table"
    const val TABLE_NAME = "vehiclemodelyear"
    const val DATABASE_NAME = "data.sqlite"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_CONTENT = "content"
    //    id    title    content

    const val DATABASE_VERSION = 1
//    const val DATABASE_NAME = "MyLessonDB.db"
    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "$COLUMN_NAME_TITLE TEXT, " +
            "$COLUMN_NAME_CONTENT TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    const val DB_PATH = "/data/data/com.example.test_test_test1/databases/"

    const val KEY_ID = "id"
    const val KEY_YEAR = "year"
    const val KEY_BRAND = "brand"
    const val KEY_MODEL = "model"

    // Общая ячейка данных
    var data: String? = ""


    // выбираем бренд, где год уже выбран и равен data
    val SQL_CHOICE_YEAR = "SELECT $KEY_BRAND FROM $TABLE_NAME WHERE $KEY_YEAR = '$data'"




    var myPath: String =
        DB_PATH + DATABASE_NAME // also check the extension of you db file

}