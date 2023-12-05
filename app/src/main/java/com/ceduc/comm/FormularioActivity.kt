package com.ceduc.comm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class FormularioActivity : AppCompatActivity() {

    private lateinit var codigoEditText: EditText
    private lateinit var descripcionEditText: EditText
    private lateinit var precioEditText: EditText
    private lateinit var database: SQLiteDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        codigoEditText = findViewById(R.id.editTextCodigo)
        descripcionEditText = findViewById(R.id.editTextDescripcion)
        precioEditText = findViewById(R.id.editTextPrecio)

        database = SQLiteDB(this)

        val agregarAlCarritoButton: Button = findViewById(R.id.agregarAlCarritoButton)
        agregarAlCarritoButton.setOnClickListener {
            val codigo = codigoEditText.text.toString()
            val descripcion = descripcionEditText.text.toString()
            val precio = precioEditText.text.toString().toDouble()

            database.agregarProducto(codigo, descripcion, precio)
        }

        val actualizarButton: Button = findViewById(R.id.botonActualizar)
        actualizarButton.setOnClickListener {
            limpiarCampos()
        }

        val volverButton: Button = findViewById(R.id.botonVolver)
        volverButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun limpiarCampos() {
        codigoEditText.text.clear()
        descripcionEditText.text.clear()
        precioEditText.text.clear()
    }
}
