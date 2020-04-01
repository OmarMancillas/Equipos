package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_datos_jugador.*

class DatosJugadorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_jugador)
        setSupportActionBar(toolbar)

        
        val nombre=intent.extras.getString("nombre","NA")
        val correoElectronico=intent.extras.getString("correoElectronico","NA")
        val equipo=intent.extras.getString("equipo","NA")
        Toast.makeText(this,"${nombre} ${correoElectronico}",Toast.LENGTH_LONG).show()
    }

    private fun miMetodo() {
        Toast.makeText(this,"se invoco a mi metodo2",Toast.LENGTH_LONG).show()
    }

}
