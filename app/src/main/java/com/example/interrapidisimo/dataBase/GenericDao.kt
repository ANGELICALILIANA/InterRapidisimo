package com.example.interrapidisimo.dataBase

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface GenericDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrUpdateRows(entity: List<T>)

}