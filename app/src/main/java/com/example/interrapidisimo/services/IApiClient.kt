package com.example.interrapidisimo.services

import com.example.interrapidisimo.data.Constant.API_KEY
import com.example.interrapidisimo.data.Constant.API_VALUE
import com.example.interrapidisimo.data.Constant.ENDPOINT
import com.example.interrapidisimo.data.TableDTO
import retrofit2.http.GET
import retrofit2.http.Headers

interface IApiClient {

    @Headers("$API_KEY: $API_VALUE")
    @GET(ENDPOINT)
    suspend fun getData(): retrofit2.Response<List<TableDTO>>

}