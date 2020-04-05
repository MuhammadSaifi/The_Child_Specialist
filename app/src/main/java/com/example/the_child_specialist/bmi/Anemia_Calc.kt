package com.example.the_child_specialist.bmi

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.example.the_child_specialist.R
import kotlinx.android.synthetic.main.anemia_calculator.*
import maes.tech.intentanim.CustomIntent

class Anemia_Calc : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anemia_calculator)

    }

    fun show(view: View) {
        val target = findViewById<EditText>(R.id.hb_level)
        val current = findViewById<EditText>(R.id.current_hb)
        val iron = findViewById<EditText>(R.id.iron)
        val anemia_kg = findViewById<EditText>(R.id.anemai_weight)
        val t = target!!.text.toString().toInt()
        val cr = current!!.text.toString().toInt()
        val k = anemia_kg!!.text.toString().toInt()
        val i = iron!!.text.toString().toInt()
        val e1 = findViewById<ImageView>(R.id.check_f1)
        val e2 = findViewById<ImageView>(R.id.check_f2)
        val e3 = findViewById<ImageView>(R.id.check_f3)
        val e4 = findViewById<ImageView>(R.id.check_f4)

        val r = k * (t - cr) * 2 * i
        board()
        val alert = AlertDialog.Builder(this)
        alert.setTitle("IRON DEFICIENCY")
        alert.setMessage("Your Iron Deficiency Is: " + r)
        alert.setPositiveButton("ok") { dialogInterface, i ->
        }
        alert.show()
    }

    override fun finish() {
        super.finish()
        CustomIntent.customType(this, "fadein-to-fadeout")
    }

    private fun board() {

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