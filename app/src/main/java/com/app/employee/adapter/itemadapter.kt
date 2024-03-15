package com.app.employee.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.employee.models.emp
import com.app.teksun.R


class ItemAdapter(private val context: Context, private val empList:  List<emp>) :
    RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {

    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var name: TextView = v.findViewById(R.id.nameE)
        var address: TextView = v.findViewById(R.id.address)
        var states: TextView = v.findViewById(R.id.states)
        var city: TextView = v.findViewById(R.id.cityE)
        var age: TextView = v.findViewById(R.id.ageE)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = empList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val emp = empList[position]
        holder.name.text = emp.name
        holder.age.text = emp.age
        holder.address.text = emp.address
        holder.city.text = emp.city
        holder.states.text = emp.states
    }
}
