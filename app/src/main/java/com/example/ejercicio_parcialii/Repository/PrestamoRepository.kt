package com.example.ejercicio_parcialii.Repository

import com.example.ejercicio_parcialii.DAO.PrestamoDao
import com.example.ejercicio_parcialii.Model.Prestamo

class PrestamoRepository(private val prestamoDao:PrestamoDao) {
    suspend fun insertar(prestamo: Prestamo){
        prestamoDao.insert(prestamo)
    }
    suspend fun getAllPrestamos(): List<Prestamo>{
        return prestamoDao.getAllPrestamos()
    }
    suspend fun deleteById(Idprestamo:Int):Int {
        return prestamoDao.deleteById(Idprestamo)
    }
    suspend fun updatePrestamo(prestamo: Prestamo) {
        prestamoDao.updatePrestamo(prestamo)
    }
}
