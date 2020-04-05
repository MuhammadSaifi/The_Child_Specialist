package com.example.the_child_specialist.display_db

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.the_child_specialist.R

class displayContact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_contact)

        title = "Disease Detail"

        val intent = intent
        val name = intent.getStringExtra("Rname")
        val phone = intent.getStringExtra("Rphone")

       val name_disease = findViewById<TextView>(R.id.name_disease)
        val description_disease = findViewById<TextView>(R.id.description_disease)
        name_disease.text = name
        description_disease.text = phone
    }

}