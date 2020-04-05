package com.example.the_child_specialist.display_db

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.the_child_specialist.R

class display_Nutrition : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_nutrition)

        title = "Nutriants Detail"

        val intent = intent
        val name2 = intent.getStringExtra("Rname")
        val phone2 = intent.getStringExtra("Rphone")

        val name_disease2 = findViewById<TextView>(R.id.name_nutriants)
        val description_disease2 = findViewById<TextView>(R.id.description_nutriants)
        name_disease2.text = name2
        description_disease2.text = phone2
    }

}