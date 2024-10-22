package com.example.ejercicio_parcialii.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ejercicio_parcialii.Repository.AutorRepository
import com.example.ejercicio_parcialii.Repository.LibroRepository

class LibroViewModelFactory(
    private val libroRepository: LibroRepository,
    private val autorRepository: AutorRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LibroViewModel::class.java)) {
            return LibroViewModel(libroRepository, autorRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
