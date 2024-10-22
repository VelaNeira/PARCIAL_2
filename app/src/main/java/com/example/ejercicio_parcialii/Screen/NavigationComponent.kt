package com.example.ejercicio_parcialii.Screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ejercicio_parcialii.Database.AutorDatabase
import com.example.ejercicio_parcialii.Database.LibroDatabase
import com.example.ejercicio_parcialii.Database.MiembroDatabase
import com.example.ejercicio_parcialii.Repository.AutorRepository
import com.example.ejercicio_parcialii.Repository.LibroRepository
import com.example.ejercicio_parcialii.Repository.MiembroRepository
import com.example.ejercicio_parcialii.ViewModel.LibroViewModel
import com.example.ejercicio_parcialii.ViewModel.LibroViewModelFactory

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val autorRepository = AutorRepository(AutorDatabase.getDatabase(context).autorDao())
    val miembroRepository = MiembroRepository(MiembroDatabase.getDatabase(context).miembroDao())
    val libroRepository = LibroRepository(LibroDatabase.getDatabase(context).libroDao())
    val libroViewModel: LibroViewModel = viewModel(
        factory = LibroViewModelFactory(libroRepository, autorRepository)
    )
    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {
            MenuScreen(navController)
        }
        composable("autores") {
            ScreenAutor(navController, autorRepository)
        }
        composable("libro") {
            ScreenLibro(navController, libroViewModel)
        }
        composable("prestamos") {
            ScreenListaPrestamo(navController)
        }
        composable("miembros") {
            ScreenMiembro(navController, miembroRepository)
        }
        composable("nuevo_prestamo") {
            ScreenPrestamo(navController)
        }
    }
}
