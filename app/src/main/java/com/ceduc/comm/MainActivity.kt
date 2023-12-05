package com.ceduc.comm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1: ImageButton = findViewById(R.id.button1)
        val button2: ImageButton = findViewById(R.id.button2)
        val button3: ImageButton = findViewById(R.id.button3)
        val button4: ImageButton = findViewById(R.id.button4)

        button1.setOnClickListener { abrirFormularioProducto("Producto 1") }
        button2.setOnClickListener { abrirFormularioProducto("Producto 2") }
        button3.setOnClickListener { abrirFormularioProducto("Producto 3") }
        button4.setOnClickListener { abrirFormularioProducto("Producto 4") }

        val botonVerCarrito: Button = findViewById(R.id.button5)
        val botonListar: Button = findViewById(R.id.button6)

        botonVerCarrito.setOnClickListener {
            verCarrito()
        }

        botonListar.setOnClickListener {
            listarProductos()
        }
    }

    private fun abrirFormularioProducto(nombreProducto: String) {
        val intent = Intent(this, FormularioActivity::class.java)
        intent.putExtra("PRODUCTO", nombreProducto)
        startActivity(intent)
    }

    private fun verCarrito() {
        val intent = Intent(this, Carrito::class.java)
        startActivity(intent)
    }

    private fun listarProductos() {
        val intent = Intent(this, ListarActivity::class.java)
        startActivity(intent)



    }
}
