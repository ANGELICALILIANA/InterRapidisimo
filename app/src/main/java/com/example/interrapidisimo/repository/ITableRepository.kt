package com.example.interrapidisimo.repository

import com.example.interrapidisimo.data.TableData

interface ITableRepository {

    suspend fun getInfoService(): List<TableData>

    suspend fun createDataBase()

    suspend fun getInformationTables(): List<TableData>

}