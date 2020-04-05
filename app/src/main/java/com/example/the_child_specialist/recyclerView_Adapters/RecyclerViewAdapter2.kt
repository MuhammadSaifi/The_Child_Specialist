package com.example.the_child_specialist.recyclerView_Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.the_child_specialist.R
import com.example.the_child_specialist.display_db.displayContact
import com.example.the_child_specialist.model_nutrition.DB_Nutrition

class RecyclerViewAdapter2 (private val context: Context, private val contactList2: ArrayList<DB_Nutrition>) : RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //what we want to see on single cart as view holder.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_nutrition, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // jub mil jay to usky ky sath kia karna hai.
        val contact = contactList2[position]

        holder.contactName2.text = contact.name2
        holder.phoneNumber2.text = contact.description2


    }

    override fun getItemCount(): Int {
        // ktny hain us mein ye btana hai.

        return contactList2.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var contactName2: TextView
        var phoneNumber2: TextView
        var icon_nutrition: ImageView

        init {
            itemView.setOnClickListener(this)
            contactName2 = itemView.findViewById(R.id.name_nutrition)
            phoneNumber2 = itemView.findViewById(R.id.phone_number2)
            icon_nutrition = itemView.findViewById(R.id.icon_Nutrition)

            icon_nutrition.setOnClickListener(this)

        }

        override fun onClick(view: View) {
            val contact = contactList2[position]
            val name2 = contact.name2
            val phone2 = contact.description2
            val intent = Intent(context, displayContact::class.java)
            intent.putExtra("Rname", name2)
            intent.putExtra("Rphone", phone2)
            context.startActivity(intent)

        }
    }
}

