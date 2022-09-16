package com.example.inframoteandroidrc.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RemoteButton::class], version = 1, exportSchema = false)
abstract class RemoteDatabase : RoomDatabase() {

    abstract val remoteButtonDao: RemoteButtonDao

    companion object {

        @Volatile
        private var INSTANCE: RemoteDatabase? = null

        fun getInstance(context: Context): RemoteDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RemoteDatabase::class.java,
                        "remote_button_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}