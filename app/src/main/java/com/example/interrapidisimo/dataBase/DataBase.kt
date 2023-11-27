package com.example.interrapidisimo.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TableEntity::class], version = 1)
abstract class TablesDatabase : RoomDatabase() {

    abstract fun getTableDao(): TableDao

}