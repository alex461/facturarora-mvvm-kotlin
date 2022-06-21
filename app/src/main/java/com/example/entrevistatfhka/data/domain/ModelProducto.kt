package com.example.entrevistatfhka.data.domain

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "producto_table")
data class ModelProducto (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val productocuenta:Int,
    val codigo: Int,
    val descripcion: String,
    val precio: Double,
    val cantidad: Int
)