package com.example.ejercicio_parcialii.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ejercicio_parcialii.Model.Autor

@Dao
interface AutorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //Revisión de conflictos entre registros
    suspend fun insert (autor: Autor)

    @Query("SELECT * FROM Autores")
    suspend fun getAllAutores(): List<Autor>

    @Query("SELECT * FROM Autores WHERE IdAutor =:id")
    suspend fun getAutorById(id:Int): Autor?

    @Query("DELETE FROM autores WHERE IdAutor = :Idautor")
    suspend fun deleteById(Idautor: Int):Int

    @Update
    suspend fun updateAutor(autor: Autor)
}