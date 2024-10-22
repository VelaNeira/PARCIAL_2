package com.example.ejercicio_parcialii.Repository

import com.example.ejercicio_parcialii.DAO.MiembroDao
import com.example.ejercicio_parcialii.Model.Autor
import com.example.ejercicio_parcialii.Model.Miembro

class MiembroRepository(private val miembroDao:MiembroDao) {
    suspend fun insertar(miembro: Miembro){
        miembroDao.insert(miembro)
    }
    suspend fun getAllMiembros(): List<Miembro>{
        return miembroDao.getAllMiembros()
    }
    suspend fun deleteById(Idmiembro:Int):Int {
        return miembroDao.deleteById(Idmiembro)
    }
    suspend fun updateMiembro(miembro: Miembro) {
        miembroDao.updateMiembro(miembro)
    }
}