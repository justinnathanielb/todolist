package org.ibda.myfragment.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TaskDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TASK_TABLE = ("CREATE TABLE $TABLE_TASKS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_NAME TEXT,"
                + "$COLUMN_PRIORITY TEXT,"
                + "$COLUMN_STAGE TEXT,"
                + "$COLUMN_DESCRIPTION TEXT,"
                + "$COLUMN_CREATED_TIME TEXT,"
                + "$COLUMN_FINISHED_TIME TEXT,"
                + "$COLUMN_DURATION TEXT" + ")")
        db.execSQL(CREATE_TASK_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TASKS")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "tasks.db"
        const val TABLE_TASKS = "tasks"

        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PRIORITY = "priority"
        const val COLUMN_STAGE = "stage"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_CREATED_TIME = "createdTime"
        const val COLUMN_FINISHED_TIME = "finishedTime"
        const val COLUMN_DURATION = "duration"
    }
}
