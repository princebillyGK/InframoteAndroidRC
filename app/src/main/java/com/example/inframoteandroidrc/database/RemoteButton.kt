package com.example.inframoteandroidrc.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "remote_button")
data class RemoteButton(
    @PrimaryKey
    val name: String,

    @ColumnInfo(name = "protocol")
    val protocol: String,

    @ColumnInfo(name = "address")
    val address: String,

    @ColumnInfo(name = "command")
    val command: String
)
