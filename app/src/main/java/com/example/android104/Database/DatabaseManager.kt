package com.example.android104.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserRecord::class], version = 1)
abstract class DatabaseManager: RoomDatabase() {
    abstract fun userRecordDao(): UserRecordDao

    companion object {
        private var INSTANCE: DatabaseManager? = null

        @JvmStatic
        fun getInstance(context: Context): DatabaseManager? {
            if (INSTANCE == null) {
                synchronized(DatabaseManager::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, DatabaseManager::class.java, "prakhar_db.db")
                            .enableMultiInstanceInvalidation()
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}