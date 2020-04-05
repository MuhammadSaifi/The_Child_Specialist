package com.example.the_child_specialist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.the_child_specialist.bmi.Anemia_Calc
import com.example.the_child_specialist.bmi.Health_Calc
import kotlinx.android.synthetic.main.calculator_fragment.view.*
import kotlinx.android.synthetic.main.calculator_fragment.view.anemia_calc
import kotlinx.android.synthetic.main.injection_fragment.view.*
import maes.tech.intentanim.CustomIntent

class Injection : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.injection_fragment,container,false)
        root.maps_display.setOnClickListener {
            val intent = Intent(root.context, MapsActivity::class.java)
            startActivity(intent)
            CustomIntent.customType(root.context, "fadein-to-fadeout")
        }
        root.more_vaccin.setOnClickListener {
            val intent = Intent(root.context, Anemia_Calc::class.java)
            startActivity(intent)
            CustomIntent.customType(root.context, "fadein-to-fadeout")
        }


        return root
    }
}