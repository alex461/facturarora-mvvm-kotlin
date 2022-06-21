package com.example.entrevistatfhka.data.domain

import androidx.room.Embedded
import androidx.room.Relation

data class ModelFactura(
@Embedded val recibo: ModelRecibo,
@Relation(
parentColumn = "cedula",
entityColumn = "productocuenta"
)
val producto: List<ModelProducto>
)
