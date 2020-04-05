package com.example.the_child_specialist.bmi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.the_child_specialist.R
import kotlinx.android.synthetic.main.calculator_fragment.view.*
import maes.tech.intentanim.CustomIntent

class Calculator : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.calculator_fragment, container, false)
        view.health_calc.setOnClickListener {
            val intent = Intent(view.context, Health_Calc::class.java)
            startActivity(intent)
            CustomIntent.customType(view.context, "fadein-to-fadeout")
        }
        view.anemia_calc.setOnClickListener {
            val intent = Intent(view.context, Anemia_Calc::class.java)
            startActivity(intent)
            CustomIntent.customType(view.context, "fadein-to-fadeout")
        }
        return view
    }
}