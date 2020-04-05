package com.example.the_child_specialist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.the_child_specialist.data.MyDbHandler
import com.example.the_child_specialist.model.Db_Disease
import com.example.the_child_specialist.recyclerView_Adapters.RecyclerViewAdapter

class Disease : Fragment() {
    private val arrayAdapter: ArrayAdapter<String>? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var contactArrayList: ArrayList<Db_Disease>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.disease_injection, container, false)


        recyclerView = view.findViewById<RecyclerView>(R.id.recyle_View)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(context)


        val db = MyDbHandler(view.context)
        contactArrayList = ArrayList()
        val alldisease = db.allContacts
        for (contact in alldisease) {

            Log.d(
                "shoaib", "Id: " + contact.id + "\n" +
                        "Disease: " + contact.name + "\n" +
                        "Description: " + contact.description + "\n"
            )
            contactArrayList!!.add(contact)
        }


        recyclerViewAdapter =
            RecyclerViewAdapter(
                view.context,
                contactArrayList!!
            )
        recyclerView!!.adapter = recyclerViewAdapter




        return view
    }

}