package com.example.interrapidisimo.data;

import com.google.gson.annotations.SerializedName

data class TableDTO(
    @SerializedName("NombreTabla")
    val name: String = "",
    @SerializedName("Pk")
    val primaryKey: String = "",
    @SerializedName("QueryCreacion")
    val query: String = "",
    @SerializedName("BatchSize")
    val size: String = "",
    @SerializedName("Filtro")
    val filter: String = "",
    @SerializedName("NumeroCampos")
    val field: String = "",
    @SerializedName("FechaActualizacionSincro")
    val updateDate: String = ""
)
