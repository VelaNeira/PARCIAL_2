package com.example.ejercicio_parcialii.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ejercicio_parcialii.Model.Autor
import com.example.ejercicio_parcialii.Model.Libro
import com.example.ejercicio_parcialii.ViewModel.LibroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenLibro(navController: NavController, libroViewModel: LibroViewModel) {
    val libros by libroViewModel.librosConAutor.observeAsState(emptyList())
    val autores by libroViewModel.autores.observeAsState(emptyList())
    var titulo by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var autorSeleccionado by remember { mutableStateOf<Autor?>(null) }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = genero,
            onValueChange = { genero = it },
            label = { Text("Género") },
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = autorSeleccionado?.let { "${it.nombre} ${it.apellido}" } ?: "",
                onValueChange = {},
                label = { Text("Seleccionar Autor") },
                readOnly = true,
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown,
                        contentDescription = null
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                autores.forEach { autor ->
                    DropdownMenuItem(
                        onClick = {
                            autorSeleccionado = autor
                            expanded = false
                        },
                        text = {
                            Text(text = "${autor.nombre} ${autor.apellido}")
                        }
                    )
                }
            }
        }
        Button(
            onClick = {
                if (autorSeleccionado != null && titulo.isNotBlank() && genero.isNotBlank()) {
                    val nuevoLibro = Libro(
                        titulo = titulo,
                        genero = genero,
                        IdAutor = autorSeleccionado!!.IdAutor
                    )
                    libroViewModel.insertarLibro(nuevoLibro)
                    titulo = ""
                    genero = ""
                    autorSeleccionado = null
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Guardar Libro")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(libros) { libroConAutor ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Título: ${libroConAutor.libro.titulo}, Autor: ${libroConAutor.nombreAutor}")
                    Row {
                        Button(onClick = {
                            libroViewModel.eliminarLibro(libroConAutor.libro.IdLibro)
                        }) {
                            Text("Eliminar")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            titulo = libroConAutor.libro.titulo
                            genero = libroConAutor.libro.genero
                            autorSeleccionado = autores.find { it.IdAutor == libroConAutor.libro.IdAutor }
                        }) {
                            Text("Editar")
                        }
                    }
                }
            }
        }
        Button(onClick = { navController.navigate("menu") }) {
            Text("Volver al Menú")
        }
    }
}