package com.nvt.bloodbank.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nvt.bloodbank.databinding.CertItemBinding
import com.nvt.bloodbank.dto.Certificates

class CertAdapter:ListAdapter<Certificates,CertAdapter.CertViewHolder>(CertDiffUtilCallBack()) {
    class CertDiffUtilCallBack:DiffUtil.ItemCallback<Certificates>(){
        override fun areItemsTheSame(oldItem: Certificates, newItem: Certificates): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: Certificates, newItem: Certificates): Boolean {
            return oldItem == newItem
        }
    }
    class CertViewHolder private constructor(var binding:CertItemBinding):RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent:ViewGroup):CertViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CertItemBinding.inflate(layoutInflater,parent,false)
                return CertViewHolder(binding)
            }
        }
        fun binding(item:Certificates){
            binding.certItem = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertViewHolder {
        return CertViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CertViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding(item)
    }
}