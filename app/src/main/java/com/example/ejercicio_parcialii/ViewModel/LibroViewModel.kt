package com.example.ejercicio_parcialii.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.ejercicio_parcialii.Model.Autor
import com.example.ejercicio_parcialii.Model.Libro
import com.example.ejercicio_parcialii.Model.LibroConAutor
import com.example.ejercicio_parcialii.Repository.AutorRepository
import com.example.ejercicio_parcialii.Repository.LibroRepository
import kotlinx.coroutines.launch

class LibroViewModel(
    private val libroRepository: LibroRepository,
    private val autorRepository: AutorRepository
) : ViewModel() {
    val librosConAutor: LiveData<List<LibroConAutor>> = liveData {
        emit(libroRepository.obtenerLibrosConAutores())
    }
    val autores: LiveData<List<Autor>> = liveData {
        emit(autorRepository.getAllAutores())
    }

    fun insertarLibro(libro: Libro) {
        viewModelScope.launch {
            libroRepository.insertar(libro)
        }
    }

    fun eliminarLibro(idLibro: Int) {
        viewModelScope.launch {
            libroRepository.eliminar(idLibro)
        }
    }
}
