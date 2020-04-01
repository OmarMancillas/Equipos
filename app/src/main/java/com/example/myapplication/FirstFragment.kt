package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val elementos = arrayOf("Norte America", "Europa", "Oceania", "Latino America")

        val adaptador =  ArrayAdapter(context, android.R.layout.simple_spinner_item, elementos)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val equipo:String

        if(rgEquipo.checkedRadioButtonId == R.id.rbEquipoRojo)
            equipo = "EQUIPO ROJO"
        else
            equipo = "EQUIPO AZUL"

        spinner.adapter = adaptador

        btnAgregar.setOnClickListener{
            val jugador = Jugador("${etNombre.text}","${etCorreoElectronico.text}","$equipo", "${spinner.selectedItem}")
            Singleton.dataSet.add(jugador)


            val intent:Intent = Intent(Intent.ACTION_SEND);
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_EMAIL, "omarmanlun94@gmail.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Se agrego un jugador");
            intent.putExtra(Intent.EXTRA_TEXT, "${jugador.nombre} se agrego al dataset.");

            startActivity(Intent.createChooser(intent, "Send Email"));

            Snackbar.make(view,"Jugador agregado al equipo ${jugador.equipo}",Snackbar.LENGTH_LONG).show()
        }
    }
}
