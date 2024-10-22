package com.example.ejercicio_parcialii.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.ejercicio_parcialii.Model.Libro
import com.example.ejercicio_parcialii.Model.Autor
import com.example.ejercicio_parcialii.Model.LibroConAutor

@Dao
interface LibrosDao {
    @Transaction
    @Query("SELECT libros.*, autores.nombre || ' ' || autores.apellido AS nombreAutor FROM libros INNER JOIN autores ON libros.IdAutor = autores.IdAutor")
    fun obtenerLibrosConAutores(): List<LibroConAutor>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(libro: Libro)

    @Update
    suspend fun actualizar(libro: Libro)

    @Query("DELETE FROM libros WHERE IdLibro = :idLibro")
    suspend fun eliminarPorId(idLibro: Int)
}
