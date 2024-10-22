package com.example.ejercicio_parcialii.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ejercicio_parcialii.Model.Autor
import com.example.ejercicio_parcialii.Repository.AutorRepository
import kotlinx.coroutines.launch

@Composable
fun ScreenAutor(navController: NavController, autorRepository: AutorRepository) {
    var autores by remember { mutableStateOf(listOf<Autor>()) }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var nacionalidad by remember { mutableStateOf("") }
    var autorId by remember { mutableStateOf<Int?>(null) } // Para almacenar el ID del autor seleccionado para editar

    // Obtener el CoroutineScope
    val coroutineScope = rememberCoroutineScope()

    // Cargar autores al inicio
    LaunchedEffect(Unit) {
        autores = autorRepository.getAllAutores()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Gestión de Autores", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Campos de texto para el autor
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = nacionalidad,
            onValueChange = { nacionalidad = it },
            label = { Text("Nacionalidad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar o actualizar un autor
        Button(
            onClick = {
                coroutineScope.launch {
                    val nuevoAutor = Autor(IdAutor = autorId ?: 0, nombre = nombre, apellido = apellido, nacionalidad = nacionalidad)
                    if (autorId == null) {
                        autorRepository.insertar(nuevoAutor) // Agregar nuevo autor
                    } else {
                        autorRepository.updateAutor(nuevoAutor) // Actualizar autor existente
                    }
                    // Limpiar los campos de texto
                    nombre = ""
                    apellido = ""
                    nacionalidad = ""
                    autorId = null // Resetear el ID después de la operación
                    // Actualizar la lista de autores
                    autores = autorRepository.getAllAutores()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (autorId == null) "Agregar Autor" else "Actualizar Autor")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de autores
        Text("Lista de Autores:", style = MaterialTheme.typography.headlineSmall)

        // Usar un LazyColumn para mejorar el rendimiento al mostrar la lista
        LazyColumn {
            items(autores) { autor ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Nombre: ${autor.nombre}", style = MaterialTheme.typography.bodyLarge)
                            Text("Apellido: ${autor.apellido}", style = MaterialTheme.typography.bodyMedium)
                            Text("Nacionalidad: ${autor.nacionalidad}", style = MaterialTheme.typography.bodyMedium)
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Botón para editar autor
                            Button(onClick = {
                                nombre = autor.nombre
                                apellido = autor.apellido
                                nacionalidad = autor.nacionalidad
                                autorId = autor.IdAutor // Establecer el ID del autor a editar
                            }) {
                                Text("Editar")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            // Botón para eliminar autor
                            Button(onClick = {
                                coroutineScope.launch {
                                    autorRepository.deleteById(autor.IdAutor)
                                    autores = autorRepository.getAllAutores()
                                }
                            }) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate("menu") }) {
            Text("Volver al Menú")
        }
    }
}