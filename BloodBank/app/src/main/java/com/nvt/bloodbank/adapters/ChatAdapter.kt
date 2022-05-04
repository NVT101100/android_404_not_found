package com.nvt.bloodbank.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.databinding.ChattingItemBinding
import com.nvt.bloodbank.dto.Chats

class ChatAdapter:ListAdapter<Chats,ChatAdapter.ChatVH>(ChatDiffUtilCallBack()) {
    class ChatDiffUtilCallBack:DiffUtil.ItemCallback<Chats>(){
        override fun areItemsTheSame(oldItem: Chats, newItem: Chats): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: Chats, newItem: Chats): Boolean {
            return false
        }

    }
    class ChatVH private constructor(var binding : ChattingItemBinding):RecyclerView.ViewHolder(binding.root){
        private val id = Firebase.auth.currentUser?.uid
        companion object {
            fun from(parent: ViewGroup):ChatVH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ChattingItemBinding.inflate(layoutInflater,parent,false)
                return ChatVH(binding)
            }
        }
        fun bind(item:Chats){
            binding.message = item
            if(item.sender == id) binding.receive.visibility = View.INVISIBLE
            else binding.send.visibility = View.INVISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatVH {
        return ChatVH.from(parent)
    }

    override fun onBindViewHolder(holder: ChatVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}