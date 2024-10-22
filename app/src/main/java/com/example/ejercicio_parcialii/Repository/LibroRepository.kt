package com.example.ejercicio_parcialii.Repository

import com.example.ejercicio_parcialii.DAO.LibrosDao
import com.example.ejercicio_parcialii.Model.Libro
import com.example.ejercicio_parcialii.Model.LibroConAutor

class LibroRepository(private val libroDao: LibrosDao) {
    suspend fun insertar(libro: Libro) {
        libroDao.insertar(libro)
    }

    fun obtenerLibrosConAutores(): List<LibroConAutor> {
        return libroDao.obtenerLibrosConAutores()
    }

    suspend fun eliminar(idLibro: Int) {
        libroDao.eliminarPorId(idLibro)
    }
}