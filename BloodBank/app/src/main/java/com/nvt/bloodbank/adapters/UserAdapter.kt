/*
package com.nvt.bloodbank.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nvt.bloodbank.R

class UserAdapter:ListAdapter<Users,UserAdapter.UserViewHolder>(UserDiffUtil()) {
    class UserDiffUtil:DiffUtil.ItemCallback<Users>(){
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        return UserViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val res = getItem(position)
        holder.bindData(res)
    }
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view){
        companion object {
            fun from(parent: ViewGroup) : UserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.search_blood_item, parent, false)
                return UserViewHolder(view)
            }
        }
        fun bindData(user:Users){
            itemView.findViewById<TextView>(R.id.searchItemName).text = user.fullname
            itemView.findViewById<TextView>(R.id.searchItemAge).text = user.age.toString()
            itemView.findViewById<TextView>(R.id.searchItemAddress).text = user.address
            itemView.findViewById<TextView>(R.id.searchItemBlood).text = user.blood.bloodGroup.name
            itemView.findViewById<TextView>(R.id.searchItemPhone).text = user.idNumber
        }
    }
}*/
