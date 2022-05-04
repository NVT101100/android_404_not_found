package com.nvt.bloodbank.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nvt.bloodbank.R
import com.nvt.bloodbank.activities.MainActivity
import com.nvt.bloodbank.databinding.ContactItemBinding
import com.nvt.bloodbank.dto.AppearContact
import com.nvt.bloodbank.fragments.ContactDirections

class ContactAdapter :
    ListAdapter<AppearContact, ContactAdapter.ContactViewHolder>(ContactDiffUtilCallBack()) {
    class ContactDiffUtilCallBack : DiffUtil.ItemCallback<AppearContact>() {
        override fun areContentsTheSame(oldItem: AppearContact, newItem: AppearContact): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: AppearContact, newItem: AppearContact): Boolean {
            return oldItem.hospName == newItem.hospName
        }
    }

    class ContactViewHolder private constructor(var binding: ContactItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ContactAdapter.ContactViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ContactItemBinding.inflate(layoutInflater, parent, false)
                return ContactAdapter.ContactViewHolder(binding)
            }
        }
        fun binding(item: AppearContact) {
            binding.appearContact = item
        }
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding(item)
        holder.binding.contactItem.setOnClickListener {
            val mainAct = holder.itemView.context as MainActivity
            val controller = Navigation.findNavController(mainAct.findViewById(R.id.nav_host))
            val action = ContactDirections.contactToChatting(item)
            controller.navigate(action)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.from(parent)
    }
}