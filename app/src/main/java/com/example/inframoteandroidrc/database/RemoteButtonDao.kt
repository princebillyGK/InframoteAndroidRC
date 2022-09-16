package com.example.inframoteandroidrc.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RemoteButtonDao {
    @Insert
    fun insert(button: RemoteButton)

    @Query("SELECT * from remote_button")
    fun getAll(): List<RemoteButton>

    @Query("DELETE FROM remote_button")
    fun clear()

    @Delete
    fun delete(button: RemoteButton)
}