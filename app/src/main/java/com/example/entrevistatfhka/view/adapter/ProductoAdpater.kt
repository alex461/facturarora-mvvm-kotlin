package com.example.entrevistatfhka.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.entrevistatfhka.R
import com.example.entrevistatfhka.data.domain.ModelProducto
import com.example.entrevistatfhka.databinding.CardProductoBinding
import java.text.DecimalFormat

class ProductoAdpater( var listaDeProductos : List<ModelProducto>) : RecyclerView.Adapter<ProductoAdpater.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CardProductoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = listaDeProductos.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val producto = listaDeProductos[position]

        with(holder.binding) {

            val dec = DecimalFormat("#,###.##")
            tvCodigo.text = String.format("Codigo : ${producto.codigo}")
            tvCantidadPrecio.text = String.format("${producto.cantidad} x ${producto.precio}")
            tvDescripcion.text = producto.descripcion
            tvTotalItem.text = dec.format(producto.precio * producto.cantidad)
        }

    }

    class MyViewHolder (val binding: CardProductoBinding) : RecyclerView.ViewHolder(binding.root)

}