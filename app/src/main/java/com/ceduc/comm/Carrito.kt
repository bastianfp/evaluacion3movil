package com.ceduc.comm

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class Carrito : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var adapter: ArrayAdapter<String>
    lateinit var database: SQLiteDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        listView = findViewById(R.id.listaProductos)
        database = SQLiteDB(this)

        mostrarProductos()

        val botonEliminar: Button = findViewById(R.id.botonEliminar)
        botonEliminar.setOnClickListener {
            eliminarProductosSeleccionados()
            mostrarProductos()
        }
    }

    private fun mostrarProductos() {
        val listaProductos: ArrayList<String> = database.obtenerProductos()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaProductos)
        listView.adapter = adapter
    }

    private fun eliminarProductosSeleccionados() {



        val listaProductos: ArrayList<String> = database.obtenerProductos()
        for (productoInfo in listaProductos) {
            val id = obtenerIdDesdeProductoInfo(productoInfo)
            database.eliminarProducto(id)
        }
    }


    private fun obtenerIdDesdeProductoInfo(productoInfo: String): Int {

        val idPart = productoInfo.substringAfter("ID: ").substringBefore(" ")
        return idPart.toInt()
    }
}
