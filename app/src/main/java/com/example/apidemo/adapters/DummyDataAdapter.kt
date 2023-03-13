package com.example.apidemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apidemo.R
import com.example.apidemo.dataclasses.dummyEMP.DummyDataClass
import com.example.apidemo.dataclasses.dummyEMP.Users
import kotlinx.android.synthetic.main.single_view_user_list.view.*

class DummyDataAdapter(val context: Context, private val employeeList: DummyDataClass, private val listener: OnItemClick): RecyclerView.Adapter<DummyDataAdapter.EmpViewHolder>() {

    class EmpViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val empImg: ImageView = itemView.imgV_empProfile
        val empID: TextView = itemView.txt_empID
        val empName: TextView = itemView.txt_empName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_view_user_list, parent, false)
        return EmpViewHolder(view)
    }

    override fun getItemCount(): Int {
        return employeeList.users.size
    }

    override fun onBindViewHolder(holder: EmpViewHolder, position: Int) {
        holder.apply {
            Glide.with(context).load(employeeList.users[position].image).into(empImg)
            empID.text = employeeList.users[position].id.toString()
            empName.text = employeeList.users[position].firstName + " " +employeeList.users[position].lastName

            itemView.setOnClickListener {
                listener.itemClicked(employeeList.users[position])
            }
        }
    }

    interface OnItemClick {
        fun itemClicked(empDetail: Users)
    }
}

