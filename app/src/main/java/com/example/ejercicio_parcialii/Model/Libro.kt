package com.example.ejercicio_parcialii.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Libros" )
data class Libro (
    @PrimaryKey(autoGenerate = true)
    val IdLibro: Int = 0
    ,val titulo: String
    ,val genero: String
    ,val IdAutor: Int
)