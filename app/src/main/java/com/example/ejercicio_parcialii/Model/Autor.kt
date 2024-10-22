package com.example.ejercicio_parcialii.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Autores" )
data class Autor (
    @PrimaryKey(autoGenerate = true)
    val IdAutor: Int = 0
    ,val nombre: String
    ,val apellido: String
    ,val nacionalidad: String
){
}