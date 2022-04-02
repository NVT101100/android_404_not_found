package com.nvt.tuan4

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ResAdapter(var context: Context): ListAdapter<Restaurants,ResAdapter.ResViewHolder>(ResDiffUtil()){
    class ResDiffUtil : DiffUtil.ItemCallback<Restaurants>(){
        override fun areItemsTheSame(oldItem: Restaurants, newItem: Restaurants): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Restaurants, newItem: Restaurants): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResViewHolder {
        return ResViewHolder.from(parent)
    }
    override fun onBindViewHolder(holder: ResViewHolder, position: Int) {
        val res = getItem(position)
        holder.bindData(res,context)
    }

    class ResViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        companion object{
            fun from(parent: ViewGroup) : ResViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.restaurant_view_item, parent, false)
                return ResViewHolder(view)
            }
        }
        fun bindData(res :  Restaurants,context: Context){
            val resName = itemView.findViewById<TextView>(R.id.itemName)
            val resAdd = itemView.findViewById<TextView>(R.id.itemAddress)
            val resImg = itemView.findViewById<ImageView>(R.id.itemImage)
            resName.text = res.name
            resAdd.text = res.address
            Glide.with(context).load(res.link).into(resImg)
        }
    }
}