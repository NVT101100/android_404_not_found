package com.nvt.bloodbank.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nvt.bloodbank.databinding.ContactItemBinding
import com.nvt.bloodbank.dto.Hospitals

class ContactAdapter :
    ListAdapter<Hospitals, ContactAdapter.ContactViewHolder>(ContactDiffUtilCallBack()) {
    class ContactDiffUtilCallBack : DiffUtil.ItemCallback<Hospitals>() {
        override fun areContentsTheSame(oldItem: Hospitals, newItem: Hospitals): Boolean {
            TODO("Not yet implemented")
        }

        override fun areItemsTheSame(oldItem: Hospitals, newItem: Hospitals): Boolean {
            TODO("Not yet implemented")
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

            fun binding(item: Hospitals) {

            }
        }
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.from(parent)
    }
}