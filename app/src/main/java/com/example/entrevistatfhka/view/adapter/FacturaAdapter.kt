package com.example.entrevistatfhka.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.entrevistatfhka.R
import com.example.entrevistatfhka.data.domain.ModelRecibo
import java.text.DecimalFormat

class FacturaAdapter : RecyclerView.Adapter<FacturaAdapter.MyViewHolder> (){

    var listFactura = ArrayList<ModelRecibo>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_producto, parent,false))

    override fun onBindViewHolder(holder: FacturaAdapter.MyViewHolder, position: Int) {
        val dec = DecimalFormat("#,###.##")

        val  factura = listFactura[position]

        holder.tvCliente.text = String.format("Cliente: ${factura.cliente}")
        holder.tvEmisor.text =String.format("Emisor: ${factura.emisor}")
        holder.tvCantidad.text = String.format("Cantidad de items: ${factura.cliente}")
        holder.tvTotal.text = String.format("Monto total ${dec.format(factura.emisor)}")
        holder.tvCliente.textSize = 18f
        holder.tvTotal.textSize = 16f    }

    override fun getItemCount()= listFactura.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){



        val tvCliente: TextView = itemView.findViewById(R.id.tv_codigo)
        val tvEmisor: TextView = itemView.findViewById(R.id.tv_cantidad_precio)
        val tvCantidad: TextView = itemView.findViewById(R.id.tv_descripcion)
        val tvTotal: TextView = itemView.findViewById(R.id.tv_total_item)
    }
}