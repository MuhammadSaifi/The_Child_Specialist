package com.example.the_child_specialist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.the_child_specialist.data_nutrition.MyDb2Handler
import com.example.the_child_specialist.model_nutrition.DB_Nutrition
import com.example.the_child_specialist.recyclerView_Adapters.RecyclerViewAdapter2

class Nutration : Fragment() {

    private var recyclerViewAdapter2: RecyclerViewAdapter2? = null
    private var recyclerView2: RecyclerView? = null
    private var ArrayList2: ArrayList<DB_Nutrition>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.nutrition_fragment, container, false)


        recyclerView2 = view.findViewById<RecyclerView>(R.id.recyle_View2)
        recyclerView2!!.setHasFixedSize(true)
        recyclerView2!!.layoutManager = LinearLayoutManager(context)


        val db2 = MyDb2Handler(view.context)
        ArrayList2 = ArrayList()
        val allnutrition = db2.allNutrition
        for (contact in allnutrition) {

            Log.d(
                "shoaib", "Id: " + contact.id2 + "\n" +
                        "Disease: " + contact.name2 + "\n" +
                        "Description: " + contact.description2 + "\n"
            )
            ArrayList2!!.add(contact)
        }


        recyclerViewAdapter2 =
            RecyclerViewAdapter2(
                view.context,
                ArrayList2!!
            )
        recyclerView2!!.adapter = recyclerViewAdapter2

        return view


    }
}