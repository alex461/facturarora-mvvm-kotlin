package com.example.entrevistatfhka.data.domain

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "recibo_table")
data class ModelRecibo(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val cedula:Int,
    var cliente: String?,
    val emisor: String?,
    val rif: String,
  )


