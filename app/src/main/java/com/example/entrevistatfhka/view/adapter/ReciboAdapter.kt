package com.example.entrevistatfhka.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.entrevistatfhka.data.domain.ModelFactura
import com.example.entrevistatfhka.databinding.CardFacturasBinding

class ReciboAdapter(var listRecibo: List<ModelFactura> ) :RecyclerView.Adapter<ReciboAdapter.MiViewHolderRecibo> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolderRecibo {
     return MiViewHolderRecibo(
         CardFacturasBinding.inflate(
             LayoutInflater.from(parent.context),parent,false
         )

     )
    }



    override fun getItemCount() = listRecibo.size

    override fun onBindViewHolder(holder: MiViewHolderRecibo, position: Int) {

        val recibo = listRecibo[position]

        with(holder.binding){

            etFacturaNombre.text = recibo.recibo.cliente

            

            etFacturaCantidad.text = "${recibo.producto.size}"


        }

    }


    class MiViewHolderRecibo(val binding: CardFacturasBinding) : RecyclerView.ViewHolder(binding.root)
}