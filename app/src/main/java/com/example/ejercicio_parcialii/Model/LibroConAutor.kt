package com.example.ejercicio_parcialii.Model

import androidx.room.Embedded

data class LibroConAutor(
    @Embedded val libro: Libro,
    val nombreAutor: String
)