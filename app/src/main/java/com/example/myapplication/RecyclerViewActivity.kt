package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_recycler_view.*
import kotlinx.android.synthetic.main.layout_jugador_recycler.view.*

class RecyclerViewActivity : AppCompatActivity(), EliminarJugadorDialogFragment.EliminarJugadorDialoglistener {

    val onLongItemClickListener: (Int) -> Unit = { position->
        Toast.makeText(this, "Eliminar a ${Singleton.dataSet.get(position).nombre}", Toast.LENGTH_SHORT).show()

        DialogEliminarJugador(position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        LoadData()

        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=JugadoresAdapter(onLongItemClickListener)
    }

    override fun onResume() {
        super.onResume()

        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun LoadData(){
        for(i in 0..20)
        {
            Singleton.dataSet.add(
                    Jugador("Jugador ${i}",
                    "${i.toString().padStart(3,'0')}@gmail.com",
//                    "${if(i%2 == 0)"Ingenieria en Sistemas Computacionales" else "Ingenieria Industrial"}")),
                            "${if(i%2 == 0)"EQUIPO ROJO" else "EQUIPO AZUL"}",
                    "Norte America"))

        }
    }

    private fun DialogEliminarJugador(position:Int){
        val dialog = EliminarJugadorDialogFragment(position)
        dialog.show(supportFragmentManager, "EliminarJugadorDialogFragment")
    }

    override fun onDialogPositiveClick(position: Int) {
        val jugador = Singleton.dataSet.get(position)
        Singleton.dataSet.removeAt(position)
//        recyclerView.adapter?.notifyItemRemoved(position)
        recyclerView.adapter?.notifyDataSetChanged()

        Snackbar.make(recyclerView,"Jugador eliminado ${jugador.nombre}",Snackbar.LENGTH_SHORT)
                .setAction("Deshacer",{
                    Singleton.dataSet.add(position,jugador)
//                    recyclerView.adapter?.notifyItemInserted(position)
                    recyclerView.adapter?.notifyDataSetChanged()
                }).show()
    }

    override fun onDialogNegativeClick(position: Int) {
        Toast.makeText(this,"No se elimino al jugador. ",Toast.LENGTH_SHORT).show()
    }
}
