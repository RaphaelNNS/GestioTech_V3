package com.example.gestiotech_v3.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gestiotech_v3.R
import com.example.gestiotech_v3.model.entities.Client

class ClientListAdapter(
    private val clientList: List<Client>
): RecyclerView.Adapter<ClientListAdapter.ClientViewHolder>() {


    inner class ClientViewHolder(itemView: View): ViewHolder(itemView){
        val textName: TextView = itemView.findViewById(R.id.textName)
        val editBtn: Button = itemView.findViewById(R.id.editBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val layoutInflater =  LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.recycler_technicians_view_item, parent, false)
        return ClientViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        var client = clientList[position]
        holder.textName.text = client.name.toString()
    }

}