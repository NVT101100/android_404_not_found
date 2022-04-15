package com.nvt.bloodbank.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nvt.bloodbank.R
import com.nvt.bloodbank.activities.MainActivity
import com.nvt.bloodbank.databinding.EventItemBinding
import com.nvt.bloodbank.dto.Events
import com.nvt.bloodbank.fragments.EventDirections

class EventAdapter:ListAdapter<Events,EventAdapter.EventViewHolder>(EventDiffUtilCallback()) {
    class EventDiffUtilCallback : DiffUtil.ItemCallback<Events>() {
        override fun areItemsTheSame(oldItem: Events, newItem: Events): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: Events, newItem: Events): Boolean {
            return oldItem == newItem
        }
    }
    class EventViewHolder private constructor(var binding:EventItemBinding)
        :RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): EventViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = EventItemBinding.inflate(layoutInflater, parent, false)
                return EventViewHolder(binding)
            }
        }
            fun binding(item:Events) {
                binding.eventObject = item
            }
        }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding(item)
        holder.binding.eventItem.setOnClickListener {
            val mainAct = holder.itemView.context as MainActivity
            val controller = Navigation.findNavController(mainAct.findViewById(R.id.nav_host))
            val action = EventDirections.eventToMap(item)
            controller.navigate(action)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder.from(parent)
    }
}