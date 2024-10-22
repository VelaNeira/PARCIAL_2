package com.example.ejercicio_parcialii.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ejercicio_parcialii.DAO.LibrosDao
import com.example.ejercicio_parcialii.Model.Autor
import com.example.ejercicio_parcialii.Model.Libro

@Database(entities = [Libro::class, Autor::class], version = 1, exportSchema = false)
abstract class LibroDatabase : RoomDatabase() {
    abstract fun libroDao(): LibrosDao

    companion object {
        @Volatile
        private var INSTANCE: LibroDatabase? = null

        fun getDatabase(context: Context): LibroDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibroDatabase::class.java,
                    "librodatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
