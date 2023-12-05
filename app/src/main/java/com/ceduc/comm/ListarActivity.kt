package com.ceduc.comm

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListarActivity : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var adapter: ArrayAdapter<String>
    lateinit var database: SQLiteDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        listView = findViewById(R.id.listaProductos)
        database = SQLiteDB(this)

        mostrarProductos()

        val botonVolver: Button = findViewById(R.id.botonVolver)
        botonVolver.setOnClickListener {
            volverAMainActivity()
        }
    }

    private fun mostrarProductos() {
        val listaProductos: ArrayList<String> = database.obtenerProductos()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaProductos)
        listView.adapter = adapter
    }

    private fun volverAMainActivity() {
        finish()
    }
}
