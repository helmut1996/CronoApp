package com.example.cronoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.cronoapp.model.Cronos
import kotlinx.coroutines.flow.Flow

@Dao
interface CronosDatabaseDao {
    @Query("SELECT * FROM CRONOS")
    fun getCronos(): Flow<List<Cronos>>

    @Query("SELECT * FROM CRONOS WHERE id = :id")
    fun getCronosById(id:Long): Flow<Cronos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(crono: Cronos)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(crono: Cronos)

    @Delete
    suspend fun delete(crono: Cronos)
}