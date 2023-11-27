package com.example.interrapidisimo.mapper

import com.example.interrapidisimo.data.TableDTO
import com.example.interrapidisimo.data.TableData
import com.example.interrapidisimo.dataBase.TableEntity

class TableDTOMapper {

     fun dtoToBusiness(dto: TableDTO): TableData {
         return TableData(
             name = dto.name,
             primaryKey = dto.primaryKey,
             query = dto.query,
             size = dto.size,
             filter = dto.filter,
             field = dto.field,
             updateDate = dto.updateDate
         )
     }

    fun businessToEntity(business: TableData): TableEntity {
         return TableEntity(
             id = 0,
             name = business.name,
             primaryKey = business.primaryKey,
             batchSize = business.size,
             field = business.field,
             updateDate = business.updateDate
         )
     }

    fun entityToBusiness(entity: TableEntity): TableData {
         return TableData(
             name = entity.name,
             primaryKey = entity.primaryKey,
             size = entity.batchSize,
             field = entity.field,
             updateDate = entity.updateDate
         )
     }

}