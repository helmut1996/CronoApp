package com.example.cronoapp.repository

import com.example.cronoapp.model.Cronos
import com.example.cronoapp.room.CronosDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CronoRepository @Inject constructor(private val cronosDatabaseDao: CronosDatabaseDao){

    suspend fun addCronos(cronos: Cronos) = cronosDatabaseDao.insert(cronos)
    suspend fun updateCronos(cronos: Cronos) = cronosDatabaseDao.update(cronos)
    suspend fun deleteCronos(cronos: Cronos) = cronosDatabaseDao.delete(cronos)
    fun getAllCronos():Flow<List<Cronos>> = cronosDatabaseDao.getCronos().flowOn(Dispatchers.IO).conflate()
    fun getCronosById(id:Long):Flow<Cronos> = cronosDatabaseDao.getCronosById(id).flowOn(Dispatchers.IO).conflate()
}