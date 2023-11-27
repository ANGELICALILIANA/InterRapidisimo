package com.example.interrapidisimo.repository

import android.content.Context
import android.system.ErrnoException
import com.example.interrapidisimo.R
import com.example.interrapidisimo.sharedpreferences.Verification
import com.example.interrapidisimo.data.TableData
import com.example.interrapidisimo.dataBase.TablesDatabase
import com.example.interrapidisimo.mapper.TableDTOMapper
import com.example.interrapidisimo.services.IApiClient
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TableEntityRepository @Inject constructor(
    private val dataBase: TablesDatabase?,
    private val apiClient : IApiClient,
    @ApplicationContext val context: Context,
): ITableRepository {

    /**
     * Obtiene información del servicio, mapea los datos, actualiza la base de datos y verifica el estado
     */
    override suspend fun getInfoService(): List<TableData> {
        val responseService = apiClient.getData()
        return if (responseService.isSuccessful) {
            val response = responseService.body()?.map { TableDTOMapper().dtoToBusiness(it) }
                ?: throw ErrnoException(context.getString(R.string.error_mapper), 0)
            response.map {
                dataBase?.getTableDao()?.createTableQuery(it.name, processResponseService(it.query))
            }
            saveInformationTablesDataBase(response)
            Verification(context).statusDataBase(true)
            response
        } else {
            Verification(context).statusDataBase(false)
            throw ErrnoException(context.getString(R.string.error_response), 1)
        }

    }

    /**
     * Procesa una cadena de consulta, eliminando espacios y saltos de línea, y agregando paréntesis al principio
     */
    private fun processResponseService(query: String): String {
        var processedString = query.substringAfter('(').replace("\r\n", "").trim()
        processedString = "($processedString"
        return processedString
    }

    /**
     * Inserta filas vacías en una tabla para abrir la base de datos al abrir la app
     */
    override suspend fun createDataBase() {
        dataBase?.getTableDao()?.insertOrUpdateRows(listOf())
    }

    /**
     * Guarda información de tablas en la base de datos, convirtiendo y actualizando las filas correspondientes
     */
    private suspend fun saveInformationTablesDataBase(names: List<TableData>) {
        dataBase?.getTableDao()?.insertOrUpdateRows(names.map{TableDTOMapper().businessToEntity(it)})
    }

    /**
     * Obtiene información de tablas desde la base de datos, verifica su existencia y actualiza el estado, devolviendo los datos convertidos
     */
    override suspend fun getInformationTables(): List<TableData>{
        val getData = dataBase?.getTableDao()?.getDataTables() ?: listOf()
        if(getData.isNotEmpty()){
            Verification(context).statusDataBase(true)
        }else{
            Verification(context).statusDataBase(false)
        }
        return getData.map { TableDTOMapper().entityToBusiness(it) }
    }

}