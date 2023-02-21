package com.example.apidemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apidemo.R
import com.example.apidemo.dataclasses.RetrofitDataClassItem
import kotlinx.android.synthetic.main.single_item.view.*

class RetrofitAdapter(val context: Context, val apiDataList: List<RetrofitDataClassItem>): RecyclerView.Adapter<RetrofitAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var userId: TextView = itemView.txt_retrofit_userID
        var title: TextView = itemView.txt_retrofit_title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.single_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return apiDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userId.text = apiDataList[position].id.toString()
        holder.title.text = apiDataList[position].title
    }
}