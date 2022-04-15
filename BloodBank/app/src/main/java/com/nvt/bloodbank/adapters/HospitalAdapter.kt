package com.nvt.bloodbank.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nvt.bloodbank.R
import com.nvt.bloodbank.activities.MainActivity
import com.nvt.bloodbank.databinding.HospitalItemBinding
import com.nvt.bloodbank.dto.Hospitals
import com.nvt.bloodbank.fragments.EventDirections
import com.nvt.bloodbank.fragments.SearchFragmentDirections

class HospitalAdapter:ListAdapter<Hospitals,HospitalAdapter.HospitalViewHolder>(HospitalDiffUtilCallBack()) {
    class HospitalDiffUtilCallBack : DiffUtil.ItemCallback<Hospitals>() {
        override fun areContentsTheSame(oldItem: Hospitals, newItem: Hospitals): Boolean {
            TODO("Not yet implemented")
        }

        override fun areItemsTheSame(oldItem: Hospitals, newItem: Hospitals): Boolean {
            TODO("Not yet implemented")
        }
    }
    class HospitalViewHolder private constructor(var binding : HospitalItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): HospitalAdapter.HospitalViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HospitalItemBinding.inflate(layoutInflater, parent, false)
                return HospitalAdapter.HospitalViewHolder(binding)
            }
        }
        fun binding(item: Hospitals) {
            binding.hospitalObject = item
        }
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding(item)
        holder.binding.hospitalItem.setOnClickListener {
            val mainAct = holder.itemView.context as MainActivity
            val controller = Navigation.findNavController(mainAct.findViewById(R.id.nav_host))
            val action = SearchFragmentDirections.searchToDetail(item)
            controller.navigate(action)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        return HospitalViewHolder.from(parent)
    }
}