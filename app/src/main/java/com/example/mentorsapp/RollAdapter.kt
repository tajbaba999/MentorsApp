package com.example.mentorsapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RollAdapter(var dataList: List<String>) : RecyclerView.Adapter<RollAdapter.ViewHolder>()
{
    fun updateData(newStdList: List<String>) {
        dataList = newStdList
        notifyDataSetChanged()
    }
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemRollView : TextView = itemView.findViewById(R.id.rollnocard)
        val itemEmailView : TextView = itemView.findViewById(R.id.emailcard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.rollno_cardview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.itemRollView.text = item
        holder.itemEmailView.text = item+"@cvr.ac.in"

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, StudentPage::class.java)
            intent.putExtra("rollNumber", item)
            context.startActivity(intent)
        }
    }
}