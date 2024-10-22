package com.example.ejercicio_parcialii.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ejercicio_parcialii.Model.Miembro

@Dao
interface MiembroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //Revisión de conflictos entre registros
    suspend fun insert (miembro: Miembro)

    @Query("SELECT * FROM miembros ")
    suspend fun getAllMiembros(): List<Miembro>

    @Query("DELETE FROM Miembros WHERE IdMiembro = :Idmiembro")
    suspend fun deleteById(Idmiembro: Int):Int

    @Update
    suspend fun updateMiembro(miembro: Miembro)
}