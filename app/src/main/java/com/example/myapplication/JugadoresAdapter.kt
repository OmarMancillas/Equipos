package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlinx.android.synthetic.main.layout_jugador_recycler.view.*

class JugadoresAdapter(private val longItemClickListener:(Int) -> Unit):RecyclerView.Adapter<JugadoresAdapter.ViewHolder>()  {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvNombre=v.tvNombre
        val tvCorreoElectronico=v.tvCorreoElectronico
        val tvServidor = v.tvServidor
        val tvEquipo = v.tvEquipo
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view.
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.layout_jugador_recycler, viewGroup, false)

        return ViewHolder(v)
    }

    override fun getItemCount()=Singleton.dataSet.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.setOnClickListener{
            val intent= Intent(viewHolder.itemView.context,DatosJugadorActivity::class.java)
            intent.putExtra("nombre",Singleton.dataSet.get(position).nombre)
            intent.putExtra("correoElectronico",Singleton.dataSet.get(position).correoElectronico)
            intent.putExtra("servidor",Singleton.dataSet.get(position).servidor)
            intent.putExtra("equipo",Singleton.dataSet.get(position).equipo)
            viewHolder.itemView.context.startActivity(intent)
        }

        viewHolder.itemView.setOnLongClickListener{
            longItemClickListener.invoke(position)
            true
        }

        viewHolder.tvNombre.text = Singleton.dataSet.get(position).nombre
        viewHolder.tvCorreoElectronico.text = Singleton.dataSet.get(position).correoElectronico
        viewHolder.tvServidor.text = Singleton.dataSet.get(position).servidor
        viewHolder.tvEquipo.text = Singleton.dataSet.get(position).equipo



        when (Singleton.dataSet.get(position).equipo.toString()){
            "EQUIPO ROJO"         -> {
                Log.i("Entra rojo",Singleton.dataSet.get(position).equipo.toString())
                viewHolder.tvEquipo.setTextColor(Color.RED)
            }
            "EQUIPO AZUL"     -> {
                Log.i("Entra azul",Singleton.dataSet.get(position).equipo.toString())
                viewHolder.tvEquipo.setTextColor(Color.BLUE)
            }
            else ->{

            }
        }
    }
}