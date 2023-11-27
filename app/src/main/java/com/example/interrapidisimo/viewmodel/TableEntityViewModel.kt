package com.example.interrapidisimo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.interrapidisimo.data.TableData
import com.example.interrapidisimo.repository.ITableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableEntityViewModel @Inject constructor(
    private val repository: ITableRepository
    ): ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)

    private val dataTables = MutableLiveData<List<TableData>>()
    fun getContentTable(): LiveData<List<TableData>> = dataTables

    fun loadTables(){
        scope.launch {
            repository.getInfoService()
        }
    }

    fun createDataBase() {
        scope.launch {
           repository.createDataBase()
        }
    }

    fun getAllInformationTables(){
        scope.launch {
            dataTables.postValue(repository.getInformationTables())
        }
    }

}