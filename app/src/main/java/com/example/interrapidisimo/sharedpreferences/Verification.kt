package com.example.interrapidisimo.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.interrapidisimo.R
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Actualiza el estado de la base de datos en SharedPreferences
 */
class Verification(
    @ApplicationContext val context: Context,
){

    val sharedPreferences: SharedPreferences = context.getSharedPreferences(context.getString(R.string.database_created), Context.MODE_PRIVATE)

    fun statusDataBase(status:Boolean){
        sharedPreferences.edit().putBoolean(context.getString(R.string.database_created), status).apply()
    }

}