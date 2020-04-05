package com.example.the_child_specialist.bmi

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.the_child_specialist.R
import maes.tech.intentanim.CustomIntent

class Health_Calc : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.health_calculator)

        val btncalc = findViewById<Button>(R.id.bmi_calc)
        val height = findViewById<EditText>(R.id.get_height)
        val weight = findViewById<EditText>(R.id.get_weight)
        val field = findViewById<ImageView>(R.id.check_field)
        val field2 = findViewById<ImageView>(R.id.check_field2)

        btncalc.setOnClickListener {
            val h = height.text.toString().toInt()
            val w = weight.text.toString().toInt()
            val c = w / h * h * 703

            when (c) {
                in 0..18 ->underweght()

                in 19..24 ->normal()

                in 25..29 ->over()
                else ->obesity()
            }
            Keyboard()
        }
    }


    override fun finish() {
        super.finish()
        CustomIntent.customType(this, "fadein-to-fadeout")
    }

    fun underweght() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("BMI")
        alert.setMessage("Your Child BMI is: UnderWeight")
        alert.setPositiveButton("ok") { dialogInterface, i ->
        }
        alert.show()
    }

    fun normal() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("BMI")
        alert.setMessage("Your Child BMI is: Normal")
        alert.setPositiveButton("ok") { dialogInterface, i ->
        }
        alert.show()
    }

    fun over() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("BMI")
        alert.setMessage("Your Child BMI is: OverWeight")
        alert.setPositiveButton("ok") { dialogInterface, i ->
        }
        alert.show()
    }

    fun obesity() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("BMI")
        alert.setMessage("Your Child BMI is: Obesity")
        alert.setPositiveButton("ok") { dialogInterface, i ->
        }
        alert.show()
    }
    private fun Keyboard() {

        // here we have created the view and get the current focus of window.
        // if view not null then our system will be get the system service
        // and after that it will close the keyboard.
        val key = this.currentFocus
        if (key != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(key.windowToken, 0)
        }

    }
}