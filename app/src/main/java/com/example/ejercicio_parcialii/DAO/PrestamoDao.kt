package com.example.ejercicio_parcialii.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ejercicio_parcialii.Model.Prestamo

@Dao

interface PrestamoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //Revisión de conflictos entre registros
    suspend fun insert (prestamo: Prestamo)

//    @Query("SELECT IdPrestamo, l.titulo, CONCAT(m.nombre, ' ', m.apellido), p.fecha_prestamo, p.fecha_devolucion FROM Prestamos As p INNER JOIN Libros As l ON l.IdLibro = p.IdLibro " +
//            "                            INNER JOIN Miembros As m ON m.IdMiembro = p.IdMiembro")
    @Query("SELECT * FROM Prestamos")
    suspend fun getAllPrestamos(): List<Prestamo>

    @Query("DELETE FROM Prestamos WHERE IdPrestamo = :Idprestamo")
    suspend fun deleteById(Idprestamo: Int):Int

    @Update
    suspend fun updatePrestamo(prestamo: Prestamo)
}