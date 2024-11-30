package com.example.contactlistexample

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistexample.adapter.ContactAdapter
import com.example.contactlistexample.data.Contact
import com.google.android.material.materialswitch.MaterialSwitch

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ContactAdapter
    private val contactList = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // Form elements find
        val etName = findViewById<EditText>(R.id.etName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etStatus = findViewById<CheckBox>(R.id.etStatus)


        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val  btnFiltrarDisponibles = findViewById<Button>(R.id.btnFiltroDisponibles)

        btnAgregar.setOnClickListener{
            contactList.add(Contact(
                nombre = etName.text.toString(),
                fono = etPhone.text.toString(),
                estado = etStatus.isChecked
            ))

/*            Toast.makeText(
                this,
                "Agrega ${etName.text}",
                Toast.LENGTH_LONG
            ).show()*/

            setRecyclerViewAdapter(contactList)
        }

        btnFiltrarDisponibles.setOnClickListener {
            val contactosFiltrados = contactList.filter { it.isAvailable }
            setRecyclerViewAdapter(contactosFiltrados)
        }

        val estaDisponible = findViewById<MaterialSwitch>(R.id.estaDisponible)
        estaDisponible.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                val contactosFiltrados = contactList.filter { it.isAvailable }
                setRecyclerViewAdapter(contactosFiltrados)
            }else{
                setRecyclerViewAdapter(contactList)
            }
        }

    }

    // RecyclerView
    private fun setRecyclerViewAdapter(contactList: List<Contact>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = ContactAdapter(contactList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

}