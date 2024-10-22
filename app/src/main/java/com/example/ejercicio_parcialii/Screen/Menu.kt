package com.example.ejercicio_parcialii.Screen

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.navigation.NavController

@Composable
fun MenuScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Menú Principal", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate("autores") }) {
            Text("Autores")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate("libro") }) {
            Text("Libros")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate("prestamos") }) {
            Text("Lista de Préstamos")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate("miembros") }) {
            Text("Miembros")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate("nuevo_prestamo") }) {
            Text("Préstamos")
        }
    }
}