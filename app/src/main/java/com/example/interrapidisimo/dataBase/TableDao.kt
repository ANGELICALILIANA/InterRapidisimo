package com.example.interrapidisimo.dataBase

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface TableDao : GenericDao<TableEntity> {

    @RawQuery(observedEntities = [TableEntity::class])
    suspend fun consultaRaw(query: SupportSQLiteQuery): List<TableEntity>
    suspend fun createTableQuery(name: String, text: String): SupportSQLiteQuery {
        val query = SimpleSQLiteQuery("CREATE TABLE IF NOT EXISTS $name$text")
        consultaRaw(query)
        return query
    }

    @Query("SELECT * FROM " + TableEntity.TABLE_NAME)
    suspend fun getDataTables(): List<TableEntity>

}