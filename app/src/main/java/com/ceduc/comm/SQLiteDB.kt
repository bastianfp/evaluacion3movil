package com.ceduc.comm

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteDB(context: Context) : SQLiteOpenHelper(context, "ProductsDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Productos (id INTEGER PRIMARY KEY AUTOINCREMENT, codigo TEXT, descripcion TEXT, precio REAL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Productos")
        onCreate(db)
    }

    fun agregarProducto(codigo: String, descripcion: String, precio: Double) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("codigo", codigo)
            put("descripcion", descripcion)
            put("precio", precio)
        }
        db.insert("Productos", null, values)
        db.close()
    }

    fun obtenerProductos(): ArrayList<String> {
        val listaProductos: ArrayList<String> = ArrayList()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM Productos", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val codigo = cursor.getString(cursor.getColumnIndexOrThrow("codigo"))
                val descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"))
                val precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio"))
                val productoInfo = "ID: $id - Código: $codigo - Descripción: $descripcion - Precio: $precio"
                listaProductos.add(productoInfo)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return listaProductos
    }

    fun eliminarProducto(id: Int): Boolean {
        val db = this.writableDatabase
        val result = db.delete("Productos", "id=?", arrayOf(id.toString()))
        db.close()
        return result != -1
    }
}
