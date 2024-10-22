package com.example.ejercicio_parcialii.Repository

import com.example.ejercicio_parcialii.DAO.AutorDao
import com.example.ejercicio_parcialii.Model.Autor

class AutorRepository(private val autorDao:AutorDao) {
    suspend fun insertar(autor: Autor){
        autorDao.insert(autor)
    }
    suspend fun getAllAutores(): List<Autor>{
        return autorDao.getAllAutores()
    }
    suspend fun deleteById(Idautor:Int):Int {
        return autorDao.deleteById(Idautor)
    }
    suspend fun updateAutor(autor: Autor) {
        autorDao.updateAutor(autor)
    }

}