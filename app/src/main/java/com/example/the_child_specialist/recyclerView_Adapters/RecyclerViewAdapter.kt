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
import com.example.the_child_specialist.model.Db_Disease

class RecyclerViewAdapter (private val context: Context, private val contactList: ArrayList<Db_Disease>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //what we want to see on single cart as view holder.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // jub mil jay to usky ky sath kia karna hai.
        val contact = contactList[position]

        holder.contactName.text = contact.name
        holder.phoneNumber.text = contact.description


    }

    override fun getItemCount(): Int {
        // ktny hain us mein ye btana hai.

        return contactList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var contactName: TextView
        var phoneNumber: TextView
        var iconButton: ImageView

        init {
            itemView.setOnClickListener(this)
            contactName = itemView.findViewById(R.id.name)
            phoneNumber = itemView.findViewById(R.id.phone_number)
            iconButton = itemView.findViewById(R.id.icon_button)

            iconButton.setOnClickListener(this)

        }

        override fun onClick(view: View) {
            val contact = contactList[position]
            val name = contact.name
            val phone = contact.description
            val intent = Intent(context, displayContact::class.java)
            intent.putExtra("Rname", name)
            intent.putExtra("Rphone", phone)
            context.startActivity(intent)

        }
    }
}

