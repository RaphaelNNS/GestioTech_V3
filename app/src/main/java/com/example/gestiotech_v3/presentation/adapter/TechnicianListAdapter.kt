package com.example.gestiotech_v3.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gestiotech_v3.R
import com.example.gestiotech_v3.model.entities.Technician
import com.example.gestiotech_v3.presentation.Activity.EditClientActivity
import com.example.gestiotech_v3.presentation.Activity.EditTechnicianActivity

class TechnicianListAdapter(
    private val teclist: List<Technician>
): RecyclerView.Adapter<TechnicianListAdapter.TechnicianViewHolder>() {


    inner class TechnicianViewHolder(itemView: View): ViewHolder(itemView){
        val textName: TextView = itemView.findViewById(R.id.textName)
        val editBtn: Button = itemView.findViewById(R.id.editBtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechnicianViewHolder {
        val layoutInflater =  LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.recycler_technicians_view_item, parent, false)
        return TechnicianViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return teclist.size
    }

    override fun onBindViewHolder(holder: TechnicianViewHolder, position: Int) {
        var tec = teclist[position]
        holder.textName.text = tec.name.toString()
        holder.editBtn.setOnClickListener{
            var intent = Intent(holder.itemView.context, EditTechnicianActivity::class.java)
            intent.putExtra("technicianId", tec.id)
            startActivity(holder.itemView.context, intent, null)
        }
    }





}